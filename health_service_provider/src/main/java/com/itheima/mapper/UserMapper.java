package com.itheima.mapper;

import com.itheima.pojo.User;

public interface UserMapper {
    public User findByUsername(String username);
}
