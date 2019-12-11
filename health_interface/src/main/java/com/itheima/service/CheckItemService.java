package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    //1.新建框,添加用户信息
    public void add(CheckItem checkItem);

    //2.分页查询
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //3.删除检查项
    void delete(Integer id);

    //4.updata
    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    //5.findall
    List<CheckItem> findAll();

}
