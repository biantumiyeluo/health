package com.itheima.service;

import com.itheima.pojo.TUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class SpringSecurityUserService implements UserDetailsService {
    //模拟数据库中的用户数据
    public static Map<String, TUser> map = new HashMap<String, TUser>();

    static {
        TUser user1 = new TUser();
        user1.setUsername("admin");
        user1.setPassword("admin");//明文密码（没有加密）

        TUser user2 = new TUser();
        user2.setUsername("xiaoming");
        user2.setPassword("1234");

        map.put(user1.getUsername(), user1);
        map.put(user2.getUsername(), user2);
    }

    //根据用户名查询用户信息
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //模拟数据库获取信息
        TUser user = map.get(username);
        if (user == null) {
            return null;
        } else {
            //用户返回给框架
            List<GrantedAuthority> list = new ArrayList<>();

            if (username.equals("admin")) {
                //授权
                list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                list.add(new SimpleGrantedAuthority("add"));
            }
            User securityUser = new User(username, "{noop}" + user.getPassword(), list);
            return securityUser;
        }
    }
}
