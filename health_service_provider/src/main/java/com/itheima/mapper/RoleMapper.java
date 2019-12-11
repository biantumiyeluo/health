package com.itheima.mapper;

import com.itheima.pojo.Role;

import java.util.Set;

public interface RoleMapper {
    public Set<Role> findByUserId(int id);
}
