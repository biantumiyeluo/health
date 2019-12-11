package com.itheima.mapper;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupMapper {
    //添加检查组
    void add(CheckGroup checkGroup);

    //连接关系
    void setCheckGroupAndCheckItem(Map map);

    //分页
    Page<CheckItem> selectByCondition(String queryString);

    Integer selectCount();

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //删除
    void deleteAssociation(Integer id);

    void delete(Integer id);

    //修改
    void edit(CheckGroup checkGroup);

    //查询检查组
    List<CheckGroup> findAll();
}
