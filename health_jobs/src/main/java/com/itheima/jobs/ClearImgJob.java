package com.itheima.jobs;

import com.itheima.constant.RedisConstant;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        //计算redis中的set集合差值
        Set<String> set = jedisPool.getResource().sdiff(
                RedisConstant.SETMEAL_PIC_RESOURCES,
                RedisConstant.SETMEAL_PIC_DB_RESOURCES
        );
        //判断是否有差值
        if (set != null) {
            for (String fileName : set) {
                //七牛云中删除图片
                QiniuUtils.deleteFileFromQiniu(fileName);
                //redis数据库中删除
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
                System.out.println("....." + fileName);
            }
        }
    }
}
