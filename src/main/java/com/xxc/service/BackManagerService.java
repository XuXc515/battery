package com.xxc.service;

import com.xxc.domain.BackManager;
import com.xxc.domain.Permission;
import com.xxc.domain.Role;
import com.xxc.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxc
 * @date 2020/8/1 - 10:00
 */
public interface BackManagerService {

    //根据用户名和密码查询用户信息
    BackManager findManagerByAccount(String managerAccount);

    //根据id查询用户信息
    BackManager findManagerById(@Param("id") Integer id);

    //查询最后一行的用户
    BackManager managerLast();

    //更新用户信息
    void updateManager(BackManager backManager);

    //分页查询所有用户
    List<BackManager> queryPageManager(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountManager();

    //根据用户账户查找子用户
    List<BackManager> findSubByAccount(String managerAccount);

    //分页查询子用户
    List<BackManager> querySubManager(@Param("curr") int curr, @Param("limit") int limit, @Param("managerAccount") String managerAccount);

    int querySubCountManager();

    //更新子用户状态
    void updateSubStatus(Integer id, String managerStatus);

    //添加子用户
    void saveSubBackManager(BackManager backManager);

    //根据用户所属查询用户
    List<BackManager> findMasterByManagerMaster(String managerMaster);


    //根据角色名查询角色id
    Integer findRoleIdByRoleName(String managerRole);

    //添加用户角色映射
    void addRoleManager(Integer roleId, Integer managerId);

    //分页查询角色信息
    List<Permission> queryPageRole(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountRole();

    //根据角色名分页查询角色
    List<Permission> queryPageFindRole(@Param("curr") int curr, @Param("limit") int limit, String managerRole);

    int queryPageCountFindRole(String managerRole);

    //根据id删除管理员
    void deleteManager(Integer id);

    //根据id批量删除管理员
    void deleteAllManager(String[] ids);

    //根据id删除角色用户映射表
    void deleteRoleManager(Integer id);

    //根据id批量删除角色用户映射表
    void deleteAllRoleManager(String[] ids);

    //查询所有角色的权限
    List<RolePermission> queryPagePermission(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountPermission();

    //更改角色权限状态
    void updatePermissionStatus(Integer id, String status, Integer deleteStatus);

    //根据角色名查询所有角色的权限
    List<RolePermission> queryPageRolePermission(@Param("curr") int curr, @Param("limit") int limit, String managerRole);

    int queryPageCountRolePermission(String managerRole);

    //添加角色
    void addRole(String managerRole, String roleDetail);

    //根据角色名称查询角色id
    Role findIdByRoleName(String managerRole);

    //查询所有权限id
    List<Integer> allPermission();

    //插入新角色的权限
    void addRolePermission(Integer roleId, Integer permissionId);


    //添加权限
    void addPermission(String perUrl, String perName);

    //获取添加权限的id
    Permission findIdByPerUrl(String perUrl);

    //删除权限
    void deletePermission(Integer id);


    //修改用户角色映射
    void changeRoleManager(Integer roleId, Integer managerId);
}
