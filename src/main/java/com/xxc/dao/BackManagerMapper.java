package com.xxc.dao;

import com.xxc.domain.BackManager;
import com.xxc.domain.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackManagerMapper {

    //根据用户名查询用户信息
    BackManager findManagerByAccount(String managerAccount);

    //根据id查询用户信息
    BackManager findManagerById(@Param("id") Integer id);

    //查询最后一行的用户
    BackManager managerLast();

    //更新用户信息
    void updateManager(BackManager backManager);

    //查询用户的角色
    BackManager findManagerRole(@Param("managerAccount") String managerAccount);

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
}