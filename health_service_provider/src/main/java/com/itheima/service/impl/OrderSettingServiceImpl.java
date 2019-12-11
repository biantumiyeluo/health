package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.OrderSettingMapper;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingMapper orderSettingMapper;

    //添加日历列表
    @Override
    public void add(List<OrderSetting> list) {
        if (list != null && list.size() > 0) {
            //1.遍历
            for (OrderSetting orderSetting : list) {
                long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //1.如果有编辑
                    orderSettingMapper.editNumberByOrderDate(orderSetting);
                } else {
                    //2.没有则添加
                    orderSettingMapper.add(orderSetting);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String dataBegin = date + "-1";
        String dataEnd = date + "-31";
        //创建map集合 
        Map map = new HashMap();
        //数据添加进集合
        map.put("dataBegin", dataBegin);
        map.put("dataEnd", dataEnd);
        //调用方法存进list对象中
        List<OrderSetting> list = orderSettingMapper.getOrderSettingByMonth(map);
        //创建arraylist集合
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            //创建hashMap
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date", orderSetting.getOrderDate().getDate());//获取日期
            orderSettingMap.put("number", orderSetting.getNumber());//获取预约总数
            orderSettingMap.put("reservations", orderSetting.getReservations());//获取预约人数 
            //存入data中
            data.add(orderSettingMap);
        }
        return data;
    }

    //修改预约人数
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        //根据日期查询信息数量
        long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
        //判断当前信息是否有值
        if (count > 0) {
            //有值,进行修改
            orderSettingMapper.editNumberByOrderDate(orderSetting);
        } else {
            //无值进行添加
            orderSettingMapper.add(orderSetting);
        }
    }
}
