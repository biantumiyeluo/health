package com.itheima.mapper;

import com.itheima.pojo.Permission;

import java.util.Set;

public interface PermissionMapper {
    public Set<Permission> findByRoleId(int roleId);
}
