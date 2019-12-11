package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.mapper.MemberMapper;
import com.itheima.mapper.OrderMapper;
import com.itheima.mapper.OrderSettingMapper;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderService;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    public Result order(Map map) throws Exception {
        //1.判断是否预约
        String orderDate = (String) map.get("orderDate");
        //2.转换数据类型
        Date date = DateUtils.parseString2Date(orderDate);
        //3.通过日期查询信息
        OrderSetting orderSetting = orderSettingMapper.findByOrderDate(date);
        //4.判断是否存在
        if (orderSetting == null) {
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        //1.比较预约人数
        int number = orderSetting.getNumber();//总人数
        int reservations = orderSetting.getReservations();//实际人数
        if (reservations >= number) {
            return new Result(false, MessageConstant.ORDER_FULL);
        }

        //1.根据手机号查询用户是否为会员
        String telephone = (String) map.get("telephone");//获取手机号
        //2.从数据库中获取对象
        Member member = memberMapper.findByTelephone(telephone);
        if (member != null) {
            //防止从夫提交预约
            Integer memberId = member.getId();
            //从map中获取套餐id
            Integer setmealId = Integer.parseInt((String) map.get("setmealId"));
            //存入套餐对象
            Order order = new Order(memberId, date, null, null, setmealId);
            //调用查询方法,返回套餐集合
            List<Order> list = orderMapper.findByCondition(order);
            if (list.size() > 0 && list != null) {
                //数据库有数据,已经预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }
        //3.没有预约对象
        orderSetting.setReservations(orderSetting.getReservations() + 1);//添加一名预约对象
        orderSettingMapper.editReservationsByOrderDate(orderSetting);//修改数据

        //4.如果不是预约对象
        if (member == null) {
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberMapper.add(member);
        }
        //保存到预约表
        Order order = new Order(
                member.getId(),
                date,
                (String) map.get("orderType"),
                Order.ORDERSTATUS_NO,
                Integer.parseInt((String) map.get("setmealId"))
        );
        //添加信息
        orderMapper.add(order);
        return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
    }

    //根据id查询数据
    @Override
    public Map findById(Integer id) throws Exception {
        Map map = orderMapper.findById4Detail(id);
        if (map != null) {
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate", DateUtils.parseDate2String(orderDate));
        }
        return map;
    }
}
