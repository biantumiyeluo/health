package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.mapper.SetmealMapper;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.itheima.service.impl
 * @ClassName: SetmealServiceImpl
 * @Author: renpengzhi
 * @Date: 2019/12/2 0002 下午 6:47
 * @Description: //TODO
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private SetmealMapper setmealMapper;

    //1.添加检查套餐
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.deleteAssociation(setmeal.getId());
        setmealMapper.add(setmeal);
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            //绑定关系
            setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        }
        ////将图片存入redis
        savePic2Redis(setmeal.getImg());
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //启动分页插件
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = setmealMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    //delete
    @Override
    public void delete(Integer id) {
        setmealMapper.deleteAssociation(id);//删除中间关系表数据
        deletePic2Redis(setmealMapper.picture(id));//删除redis中的图片
        QiniuUtils.deleteFileFromQiniu(setmealMapper.picture(id));
        setmealMapper.delete(id);
    }


    //建立连接关系
    public void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (Integer checkgroupId : checkgroupIds) {
                HashMap<String, Integer> map = new HashMap<>();
                map.put("setmeal_id", id);
                map.put("checkgroup_id", checkgroupId);
                setmealMapper.setSetmealAndCheckGroup(map);
            }
        }
    }

    //将图片名称保存到Redis
    private void savePic2Redis(String pic) {
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, pic);
    }

    //将图片删除
    private void deletePic2Redis(String pic) {
        jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_DB_RESOURCES, pic);
    }


    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsByCheckSetmealId(Integer id) {
        return setmealMapper.findCheckGroupIdsByCheckSetmealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        //获取数据库之前保存的图片name
        String fileName = setmealMapper.picture(setmeal.getId());
        //删除中间关系
        setmealMapper.deleteAssociation(setmeal.getId());
        //建立关系,插入数据
        setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        //编辑
        setmealMapper.edit(setmeal);
        //存入图片
        savePic2Redis(setmeal.getImg());
        //保存后的图片
        String picture = setmealMapper.picture(setmeal.getId());
        if (!picture.equals(fileName)) {
            //删除图片
            deletePic2Redis(fileName);
        }
    }

    //移动端
    @Override
    public List<Setmeal> findAll() {
        return setmealMapper.findAll();
    }

    //查询套餐
    @Override
    public Setmeal findSetmealById(Integer id) {
        return setmealMapper.findSetmealById(id);
    }

    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return setmealMapper.findSetmealCount();
    }


}
