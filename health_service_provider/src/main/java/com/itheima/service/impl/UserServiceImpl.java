package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.mapper.PermissionMapper;
import com.itheima.mapper.RoleMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return null;
        }
        //有用户
        Integer userId = user.getId();
        //根据用户id查找角色
        Set<Role> roles = roleMapper.findByUserId(userId);
        if (roles.size() > 0 || roles != null) {
            for (Role role : roles) {
                //根据角色查找权限id
                Integer permissionId = role.getId();
                Set<Permission> permissions = permissionMapper.findByRoleId(permissionId);
                if (permissions.size() > 0 || permissions != null) {
                    //授权
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;
    }
}
