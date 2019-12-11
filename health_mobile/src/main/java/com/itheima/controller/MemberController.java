package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    private MemberService memberService;
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("login")
    public Result login(HttpServletResponse response, @RequestBody Map map) {
        //获取手机号
        String telephone = (String) map.get("telephone");
        //获取验证码
        String validateCode = (String) map.get("validateCode");
        //从缓存中获取验证码
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);
        //验证按校验
        if (codeInRedis == null || !codeInRedis.equals(validateCode)) {
            //验证失败
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        } else {
            Member member = memberService.findByTelephone(telephone);
            if (member == null) {//如果不是会员
                member = new Member();
                member.setPhoneNumber(telephone);
                member.setRegTime(new Date());//注册时间
                memberService.add(member);//添加会员信息
            }
            //存储到cookie中
            Cookie login_member_telephone = new Cookie("login_member_telephone", telephone);
            login_member_telephone.setPath("/");
            login_member_telephone.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(login_member_telephone);
            //存到缓存中
            String json = JSON.toJSON(member).toString();//阿里 fastJson
            jedisPool.getResource().setex(telephone, 60 * 60 * 24 * 30, json);
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        }
    }
}
