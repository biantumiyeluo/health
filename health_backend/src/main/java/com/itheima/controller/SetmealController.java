package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @PackageName: com.itheima.controller
 * @ClassName: SetmealController
 * @Author: renpengzhi
 * @Date: 2019/12/2 0002 下午 6:44
 * @Description: //TODO
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private JedisPool jedisPool;
    @Reference
    private SetmealService setmealService;

    //1.上传图片
    @RequestMapping("upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        //1.获取原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        //2.格式  .jsp
        int lastIndex = originalFilename.lastIndexOf(".");
        //3.获取后缀
        String suffix = originalFilename.substring(lastIndex);
        //4.随机生成编码,防止重名
        String fileName = UUID.randomUUID().toString() + suffix;
        //5.调用七牛云工具类上传文件
        try {
            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            //ALiYunUtils.writeFile(imgFile.getBytes(), fileName);
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
            //保存文件名传给前台
            result.setData(fileName);
            //////存入redis,使用set集合存储
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //2.添加信息
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setmealService.add(setmeal, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    //3.分页
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    //4.删除信息
    @RequestMapping("delete")
    public Result delete(Integer id) {
        try {
            setmealService.delete(id);
            return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }

    //5.修改
    @RequestMapping("findById")
    public Result findById(Integer id) {
        Setmeal setmeal = setmealService.findById(id);
        if (setmeal != null) {
            Result result = new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS);
            result.setData(setmeal);
            return result;
        }
        return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
    }

    @RequestMapping("findCheckGroupIdsByCheckSetmealId")
    public Result findCheckGroupIdsByCheckSetmealId(Integer id) {
        try {
            List<Integer> checkgroupIds = setmealService.findCheckGroupIdsByCheckSetmealId(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("edit")
    public Result edit(@RequestBody Setmeal setmeal, Integer[] checkgroupIds) {
        try {
            setmealService.edit(setmeal, checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

}