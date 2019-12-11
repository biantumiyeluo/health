package com.itheima.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("authenty")
public class PageAuthentyController {

    @RequestMapping("checkitem")
    public Map<String, Boolean> checkitem() {
        Map<String, Boolean> map = new HashMap<>();
        //默认数值
        map.put("add", false);
        map.put("edit", false);
        map.put("delete", false);
        //获取权限集合
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取list集合
        Collection<GrantedAuthority> list = user.getAuthorities();
        for (GrantedAuthority grantedAuthority : list) {
            //获取角色权限关键字
            String keyword = grantedAuthority.getAuthority();
            if ("CHECKITEM_ADD".equals(keyword)) {
                map.put("add", true);
            }
            if ("CHECKITEM_EDIT".equals(keyword)) {
                map.put("edit", true);
            }
            if ("CHECKITEM_DELETE".equals(keyword)) {
                map.put("delete", true);
            }
        }
        return map;
    }
}
