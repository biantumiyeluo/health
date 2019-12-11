package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    //1.xlsx添加方法
    void add(List<OrderSetting> list);

    //2.查询月份
    List<Map> getOrderSettingByMonth(String date);

    //3.修改预约人数
    void editNumberByDate(OrderSetting orderSetting);
}
