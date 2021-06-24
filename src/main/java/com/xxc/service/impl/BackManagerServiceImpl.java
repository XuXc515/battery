package com.xxc.service.impl;

import com.xxc.dao.*;
import com.xxc.domain.BackManager;
import com.xxc.domain.Permission;
import com.xxc.domain.Role;
import com.xxc.domain.RolePermission;
import com.xxc.service.BackManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;


/**
 * @author xxc
 * @date 2020/8/1 - 10:00
 */
@Service("backManagerService")
public class BackManagerServiceImpl implements BackManagerService {

    @Resource
    private BackManagerMapper backManagerMapper;

    @Resource
    private RoleBackManagerMapper roleBackManagerMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Override
    public BackManager findManagerByAccount(String managerAccount) {
//        BackManager backManager = (BackManager) valueOperations.get(managerAccount);
//        if (backManager == null) {
//            BackManager manager = backManagerMapper.findManagerByAccount(managerAccount);
//            valueOperations.set(managerAccount, manager);
//            return manager;
//        } else
//            return backManager;
        return backManagerMapper.findManagerByAccount(managerAccount);
    }

    @Override
    public BackManager findManagerById(Integer id) {
        return backManagerMapper.findManagerById(id);
    }

    @Override
    public BackManager managerLast() {
        return backManagerMapper.managerLast();
    }

    @Override
    public void updateManager(BackManager backManager) {
        backManagerMapper.updateManager(backManager);
    }

    @Override
    public List<BackManager> queryPageManager(int curr, int limit) {
        curr = (curr - 1) * limit;
        return backManagerMapper.queryPageManager(curr, limit);
    }

    @Override
    public int queryPageCountManager() {
        return backManagerMapper.queryPageCountManager();
    }

    @Override
    public List<BackManager> findSubByAccount(String managerAccount) {
        return backManagerMapper.findSubByAccount(managerAccount);
    }

    @Override
    public List<BackManager> querySubManager(int curr, int limit, String managerAccount) {
        curr = (curr - 1) * limit;
        return backManagerMapper.querySubManager(curr, limit, managerAccount);
    }

    @Override
    public int querySubCountManager() {
        return backManagerMapper.querySubCountManager();
    }

    @Override
    public void updateSubStatus(Integer id, String managerStatus) {
        backManagerMapper.updateSubStatus(id, managerStatus);
    }

    @Override
    public void saveSubBackManager(BackManager backManager) {
        backManagerMapper.saveSubBackManager(backManager);
    }

    @Override
    public List<BackManager> findMasterByManagerMaster(String managerMaster) {
        return backManagerMapper.findMasterByManagerMaster(managerMaster);
    }

    @Override
    public Integer findRoleIdByRoleName(String managerRole) {
        return roleMapper.findRoleIdByRoleName(managerRole);
    }

    @Override
    public void addRoleManager(Integer roleId, Integer managerId) {
        roleBackManagerMapper.addRoleManager(roleId, managerId);
    }

    @Override
    public List<Permission> queryPageRole(int curr, int limit) {
        curr = (curr - 1) * limit;
        return backManagerMapper.queryPageRole(curr, limit);
    }

    @Override
    public int queryPageCountRole() {
        return backManagerMapper.queryPageCountRole();
    }

    @Override
    public List<Permission> queryPageFindRole(int curr, int limit, String managerRole) {
        curr = (curr - 1) * limit;
        return backManagerMapper.queryPageFindRole(curr, limit, managerRole);
    }

    @Override
    public int queryPageCountFindRole(String managerRole) {
        return backManagerMapper.queryPageCountFindRole(managerRole);
    }

    @Override
    public void deleteManager(Integer id) {
        backManagerMapper.deleteManager(id);
    }

    @Override
    public void deleteAllManager(String[] ids) {
        backManagerMapper.deleteAllManager(ids);
    }

    @Override
    public void deleteRoleManager(Integer id) {
        roleBackManagerMapper.deleteRoleManager(id);
    }

    @Override
    public void deleteAllRoleManager(String[] ids) {
        roleBackManagerMapper.deleteAllRoleManager(ids);
    }

    @Override
    public List<RolePermission> queryPagePermission(int curr, int limit) {
        curr = (curr - 1) * limit;
        return rolePermissionMapper.queryPagePermission(curr, limit);
    }

    @Override
    public int queryPageCountPermission() {
        return rolePermissionMapper.queryPageCountPermission();
    }

    @Override
    public void updatePermissionStatus(Integer id, String status, Integer deleteStatus) {
        rolePermissionMapper.updatePermissionStatus(id, status, deleteStatus);
    }

    @Override
    public List<RolePermission> queryPageRolePermission(int curr, int limit, String managerRole) {
        curr = (curr - 1) * limit;
        return rolePermissionMapper.queryPageRolePermission(curr, limit, managerRole);
    }

    @Override
    public int queryPageCountRolePermission(String managerRole) {
        return rolePermissionMapper.queryPageCountRolePermission(managerRole);
    }

    @Override
    public void addRole(String managerRole, String roleDetail) {
        roleMapper.addRole(managerRole, roleDetail);
    }

    @Override
    public Role findIdByRoleName(String managerRole) {
        return roleMapper.findIdByRoleName(managerRole);
    }

    @Override
    public List<Integer> allPermission() {
        return permissionMapper.allPermission();
    }

    @Override
    public void addRolePermission(Integer roleId, Integer permissionId) {
        rolePermissionMapper.addRolePermission(roleId, permissionId);
    }

    @Override
    public void addPermission(String perUrl, String perName) {
        permissionMapper.addPermission(perUrl, perName);
    }

    @Override
    public Permission findIdByPerUrl(String perUrl) {
        return permissionMapper.findIdByPerUrl(perUrl);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionMapper.deletePermission(id);
    }

    @Override
    public void changeRoleManager(Integer roleId, Integer managerId) {
        roleBackManagerMapper.changeRoleManager(roleId, managerId);
    }

}
