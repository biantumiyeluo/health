package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.mapper.CheckItemMapper;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @PackageName: com.itheima.service.impl
 * @ClassName: CheckItemServiceImpl
 * @Author: renpengzhi
 * @Date: 2019/11/29 0029 下午 6:05
 * @Description: //TODO
 */
@Service(interfaceClass = CheckItemService.class)  //dubbo服务器远程调用,声明接口类文件
@Transactional  //事务控制注解
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    //1.添加
    @Override
    public void add(CheckItem checkItem) {
        checkItemMapper.add(checkItem);
    }

    //2.分页查询
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);//启动分页插件
        Page<CheckItem> page = checkItemMapper.selectByCondition(queryString);
        long total = page.getTotal();//总记录数
        /*//查询记录数
        Integer count = checkItemMapper.selectCount(queryString);
        if (count > 0) {
            int num = (count / page.getPageSize() == 0 ? count / page.getPageSize() : (count / page.getPageSize() + 1));
        }*/
        return new PageResult(total, page.getResult());
    }

    //3.删除检查项
    @Override
    public void delete(Integer id) throws RuntimeException {
        if (checkItemMapper.findCountByCheckItemId(id) > 0) { //查询是否有关联
            throw new RuntimeException("检查被引用,不能删除");
        }
        checkItemMapper.delete(id);
    }

    //4.update
    @Override
    public CheckItem findById(Integer id) {
        return checkItemMapper.findById(id);
    }

    //修改检查项
    @Override
    public void edit(CheckItem checkItem) {
        checkItemMapper.edit(checkItem);
    }

    //查询所有
    @Override
    public List<CheckItem> findAll() {
        return checkItemMapper.findAll();
    }

}
