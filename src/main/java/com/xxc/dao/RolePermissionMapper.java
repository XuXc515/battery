package com.xxc.dao;

import com.xxc.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {

    //查询所有角色的权限
    List<RolePermission> queryPagePermission(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountPermission();

    //更改角色权限状态
    void updatePermissionStatus(Integer id, String status, Integer deleteStatus);

    //根据角色名查询所有角色的权限
    List<RolePermission> queryPageRolePermission(@Param("curr") int curr, @Param("limit") int limit, @Param("managerRole") String managerRole);

    int queryPageCountRolePermission(String managerRole);

    //插入新角色的权限
    void addRolePermission(Integer roleId, Integer permissionId);
}