package com.xxc.dao;

import com.xxc.domain.Permission;

import java.util.List;

public interface PermissionMapper {

    //查询用户的权限
    List<Permission> findPermissionByAccount(String managerAccount);

    //查询用户的权限
    List<Permission> findUserPermission(String userRole);

    //查询所有权限id
    List<Integer> allPermission();

    //添加权限
    void addPermission(String perUrl, String perName);

    //获取添加权限的id
    Permission findIdByPerUrl(String perUrl);

    //删除权限
    void deletePermission(Integer id);
}