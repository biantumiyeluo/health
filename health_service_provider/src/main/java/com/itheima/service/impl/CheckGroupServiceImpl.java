package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.PageResult;
import com.itheima.mapper.CheckGroupMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @PackageName: com.itheima.service.impl
 * @ClassName: CheckGroupServiceImpl
 * @Author: renpengzhi
 * @Date: 2019/11/30 0030 下午 8:36
 * @Description: //TODO
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional//事务管理
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupMapper checkGroupMapper;

    //1.添加检查组
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupMapper.deleteAssociation(checkGroup.getId());
        checkGroupMapper.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    //检查组和检查项的关系
    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        //非空判断
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                //创建hashMap集合存储数据
                HashMap<String, Integer> map = new HashMap<>();
                map.put("checkgroup_id", checkGroupId);
                map.put("checkitem_id", checkitemId);
                checkGroupMapper.setCheckGroupAndCheckItem(map);
            }
        }
    }

    //2.分页查询
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        Integer count = checkGroupMapper.selectCount();
        if (count > 0) {
            currentPage = 1;
        }
        //1.启动分页插件
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = checkGroupMapper.selectByCondition(queryString);
        //2.返回分页结果
        return new PageResult(page.getTotal(), page.getResult());
    }

    //根据id查询
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.findById(id);
    }

    //查询关联ids
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupMapper.findCheckItemIdsByCheckGroupId(id);
    }

    //编辑检查组
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupMapper.deleteAssociation(checkGroup.getId());//删除中间表数据
        //建立连接关系
        setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
        //更新数据
        checkGroupMapper.edit(checkGroup);
    }

    @Override
    public void delete(Integer id) {
        checkGroupMapper.deleteAssociation(id);
        checkGroupMapper.delete(id);
    }

    //查询检查组
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.findAll();
    }

}
