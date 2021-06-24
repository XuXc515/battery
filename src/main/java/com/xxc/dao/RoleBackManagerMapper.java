package com.xxc.dao;

import com.xxc.domain.RoleBackManager;

public interface RoleBackManagerMapper {

    /*
    backManagerService
     */
    //根据id删除角色用户映射表
    void deleteRoleManager(Integer id);

    //根据id批量删除角色用户映射表
    void deleteAllRoleManager(String[] ids);

    //添加用户角色映射
    void addRoleManager(Integer roleId, Integer managerId);

    //修改用户角色映射
    void changeRoleManager(Integer roleId, Integer managerId);

}