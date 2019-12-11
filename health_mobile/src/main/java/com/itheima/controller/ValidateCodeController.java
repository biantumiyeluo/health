package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;//使用缓存数据库,缓存手机号

    @RequestMapping("send4Order")
    public Result send4Order(String telephone) {
        //生成4位短信
        Integer code = ValidateCodeUtils.generateValidateCode(4);

        try {
            //调用发送短息方法
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //将数据存入缓存数据库
        jedisPool.getResource().setex(
                telephone + RedisMessageConstant.SENDTYPE_ORDER,
                60 * 60 * 24 * 10,
                code.toString()
        );
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }

    //手机验证码
    @RequestMapping("send4Login")
    public Result send4Login(String telephone) {
        Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6为主验证码
        try {
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE, telephone, code.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //存储验证码
        jedisPool.getResource().setex(
                telephone + RedisMessageConstant.SENDTYPE_LOGIN,
                60 * 60 * 24 * 10,
                code.toString()
        );
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
