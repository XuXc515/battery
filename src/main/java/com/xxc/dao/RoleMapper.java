package com.xxc.dao;

import com.xxc.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface RoleMapper {

    //查询用户角色
    List<Role> findRoleByAccount(String managerAccount);


    /*
    backManagerService
     */
    //根据角色名查询角色id
    Integer findRoleIdByRoleName(String managerRole);

    //添加角色
    void addRole(String managerRole, String roleDetail);

    //根据角色名称查询角色id
    Role findIdByRoleName(String managerRole);
}