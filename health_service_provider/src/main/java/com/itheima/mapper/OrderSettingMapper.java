package com.itheima.mapper;

import com.itheima.pojo.OrderSetting;
import com.itheima.pojo.Setmeal;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingMapper {
    //1.查询日历日期
    long findCountByOrderDate(Date orderDate);

    OrderSetting findByOrderDate(Date date);

    //2.修改
    void editNumberByOrderDate(OrderSetting orderSetting);

    //3.添加
    void add(OrderSetting orderSetting);

    //4.根据月份查看预约信息
    List<OrderSetting> getOrderSettingByMonth(Map map);

    //5.修改人数
    void editReservationsByOrderDate(OrderSetting orderSetting);
}
