package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    //1.添加检查组
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    //2.分页查询
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //根据id查询
    CheckGroup findById(Integer id);

    //查询关联ids
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //编辑检查组
    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    //删除
    void delete(Integer id);

    //查询所有检查组
    List<CheckGroup> findAll();
}
