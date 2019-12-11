package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealMapper {
    //建立连接关系
    void setSetmealAndCheckGroup(Map map);

    //添加
    void add(Setmeal setmeal);

    //分页查询方法
    Page<CheckItem> selectByCondition(String queryString);

    //delete
    void deleteAssociation(Integer id);//删除中间表的关系

    void delete(Integer id);

    //查询图片
    String picture(Integer id);

    Setmeal findById(Integer id);

    List<Integer> findCheckGroupIdsByCheckSetmealId(Integer id);

    void edit(Setmeal setmeal);

    //移动端
    List<Setmeal> findAll();

    //查询移动端套餐
    Setmeal findSetmealById(Integer id);

    //查询套餐记录数
    List<Map<String, Object>> findSetmealCount();
}
