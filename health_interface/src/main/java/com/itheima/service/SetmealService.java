package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    //套餐添加
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    //分页数据
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //delete
    void delete(Integer id);

    Setmeal findById(Integer id);

    List<Integer> findCheckGroupIdsByCheckSetmealId(Integer id);

    void edit(Setmeal setmeal, Integer[] checkgroupIds);

    //移动端
    List<Setmeal> findAll();

    Setmeal findSetmealById(Integer id);

    //获取套餐记录数
    List<Map<String, Object>> findSetmealCount();

}
