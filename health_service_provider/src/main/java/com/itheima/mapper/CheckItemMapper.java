package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemMapper {
    //1.添加用户信息
    public void add(CheckItem checkItem);

    //2.分页查询
    Page<CheckItem> selectByCondition(String queryString);

    //模糊查询记录数
    Integer selectCount(String queryString);

    //3.删除
    void delete(Integer id);

    long findCountByCheckItemId(Integer checkItemId);

    //4.update
    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    //5.findAll
    List<CheckItem> findAll();

}
