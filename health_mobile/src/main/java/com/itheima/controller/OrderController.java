package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import com.itheima.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    @RequestMapping("submit")
    public Result submitOrder(@RequestBody Map map) {
        //1.获取电话号码
        String telephone = (String) map.get("telephone");
        //2.从缓存中查询验证码
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        //3.获取输入的验证码
        String validateCode = (String) map.get("validateCode");
        //4.判断输入验证码是否相同
        if (validateCode == null && !codeInRedis.equals(validateCode)) {
            //验证失败
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result = null;
        //5.体检预约服务
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);//微信预约
            result = orderService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        //6.
        if (result.isFlag()) {
            //获取预约日期
            String orderDate = (String) map.get("orderDate");
            //预约成功
            try {
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE, telephone, orderDate);
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping("findById")
    public Result findById(Integer id) {
        try {
            //根据id查询数据
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
