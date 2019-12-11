package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

public interface OrderService {
    //1.返回检查结果
    public Result order(Map map) throws Exception;

    //2.根据id查询数据
    Map findById(Integer id) throws Exception;
}
