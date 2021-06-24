package com.xxc.controller;

import com.xxc.authentication.JWTUtil;
import com.xxc.domain.*;
import com.xxc.service.BackManagerService;
import com.xxc.service.UserService;
import com.xxc.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xxc
 * @date 2020/8/1 - 9:45
 */
@RestController
@RequestMapping("/backManager")
public class BackManagerController extends BaseController {

    @Resource
    private BackManagerService backManagerService;

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(String managerAccount, String password,
                                     HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        //将用户名密码加密比对
        String account = StringUtils.lowerCase(managerAccount);
        password = MD5Util.encrypt(account, password);

        BackManager manager = backManagerService.findManagerByAccount(managerAccount);

        if (password.equals(manager.getManagerPassword())) {
            map.put("manager", manager);
            // 生成 Token
            response.setHeader("TOKEN", JWTUtil.sign(managerAccount, password));
        } else {
            map.put("msg", "Username or password is incorrect");
        }

        return map;
    }


    //根据用户名查询用户,在页面回显
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:findByAccount")
    @GetMapping("/findByAccount")
    public BackManager findManagerByAccount(String managerAccount,
                                            HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        BackManager backManager = backManagerService.findManagerByAccount(managerAccount);

        return backManager;
    }

    //修改个人信息
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:changeSelf")
    @PutMapping("/changeSelf")
    public Map<String, Object> updateManagerSelf(BackManager backManager,
                                                 HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token，判断语言
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        //得到用户账户和用户密码，对密码进行MD5盐值加密
        String saltPassword = MD5Util.encrypt(backManager.getManagerAccount(), backManager.getManagerPassword());
        backManager.setManagerPassword(saltPassword);

        //更新用户信息
        backManagerService.updateManager(backManager);
        //将用户的密码更新到前端，为下次token验证
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("managerPassword", saltPassword);

        if (!lang)
            map.put("msg", "Successfully changed");
        else
            map.put("msg", "修改成功");

        return map;
    }

    //查询所有子用户返回列表页面
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:subs")
    @PostMapping("/subs")
    public Map<String, Object> subs(int curr, int limit,
                                    HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<BackManager> page = backManagerService.queryPageManager(curr, limit);

        //总数居条数
        int pageCount = backManagerService.queryPageCountManager();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //查询超级管理员root用户的子用户
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:subBackManager")
    @GetMapping("/subBackManager")
    public List<BackManager> subBackManager(String account,
                                            HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        List<BackManager> subs = backManagerService.findSubByAccount(account);

        return subs;
    }

    //查询所有超级管理员root
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:subMaster")
    @GetMapping("/subMaster")
    public List<BackManager> subMaster(String account,
                                       HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        List<BackManager> subs = backManagerService.findSubByAccount(account);

        return subs;
    }

    //查询所有主用户下的用户返回列表页面
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:subTable")
    @PostMapping("/subTable")
    public Map<String, Object> subTable(int curr, int limit, String account,
                                        HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<BackManager> page = backManagerService.querySubManager(curr, limit, account);

        //总数居条数
        int pageCount = backManagerService.querySubCountManager();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //修改子用户状态
    @RequiresRoles("root")
    @RequiresPermissions("backManager:subStatus")
    @PutMapping("/subStatus")
    public Map<String, Object> subStatus(Integer id, String managerStatus,
                                         HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        BackManager manager = backManagerService.findManagerById(id);

        Map<String, Object> map = new HashMap<String, Object>();
        //判断用户所属是否为none，true为root，不能修改
        if (manager.getManagerMaster().equals("none"))
            if (!lang)
                map.put("msg", "Permission Denied！");
            else
                map.put("msg", "你没有权限！");
        else {
            backManagerService.updateSubStatus(id, managerStatus);
            if (!lang)
                map.put("msg", "Successfully changed");
            else
                map.put("msg", "修改成功");
        }
        return map;
    }

    //添加子用户
    @RequiresRoles("root")
    @RequiresPermissions("backManager:add")
    @PostMapping("/add")
    public String addUser(BackManager backManager,
                          HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");
        //判断是否有同名账户
        BackManager manager = backManagerService.findManagerByAccount(backManager.getManagerAccount());
        User user = userService.findBySign(backManager.getManagerAccount());
        if (manager == null && user == null) {
            //生成设置加密密码
            String account = StringUtils.lowerCase(backManager.getManagerAccount());
            String password = MD5Util.encrypt(account, backManager.getManagerPassword());
            backManager.setManagerPassword(password);

            //生成设置用户编号
            BackManager managerLast = backManagerService.managerLast();
            Integer managerLastId = Integer.parseInt(managerLast.getManagerId()) + 1;
            backManager.setManagerId(managerLastId.toString());

            //根据传来的master，查询master值
            List<BackManager> list = backManagerService.findMasterByManagerMaster(backManager.getManagerMaster());
            String master = list.get(0).getManagerMaster();

            //如果根据master查询到用户，并且用户的master值不为none
            if (list != null && !master.equals("none")) {

                //保存用户
                backManagerService.saveSubBackManager(backManager);

                //在角色映射表中保存 角色——用户 映射
                String role = backManager.getRole().getManagerRole();
                int roleId = backManagerService.findRoleIdByRoleName(role);
                BackManager thisBackManager = backManagerService.findManagerByAccount(backManager.getManagerAccount());
                Integer managerId = thisBackManager.getId();

                backManagerService.addRoleManager(roleId, managerId);

                if (!lang)
                    return "Added successfully";
                else
                    return "添加成功";
            } else if (!lang)
                return "Master Error";
            else
                return "用户所属错误";
        } else if (!lang)
            return "Username already exists";
        else
            return "用户名已存在";

    }

    //查询所有用户返回列表页面
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:roles")
    @PostMapping("/roles")
    public Map<String, Object> roles(int curr, int limit,
                                     HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Permission> page = backManagerService.queryPageRole(curr, limit);

        //总数居条数
        int pageCount = backManagerService.queryPageCountRole();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }


    //根据角色名称查询所有角色
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:findRole")
    @PostMapping("/findRole")
    public Map<String, Object> findRole(int curr, int limit, String managerRole,
                                        HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Permission> page = backManagerService.queryPageFindRole(curr, limit, managerRole);

        //总数居条数
        int pageCount = backManagerService.queryPageCountFindRole(managerRole);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //删除用户角色
    @RequiresRoles("root")
    @RequiresPermissions("backManager:deleteRole")
    @DeleteMapping("/deleteRole")
    public String deleteRole(Integer roleBackManagerId, Integer backManagerId,
                             HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");
        //删除用户和用户——角色映射
        backManagerService.deleteManager(backManagerId);
        backManagerService.deleteRoleManager(roleBackManagerId);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";

    }

    //批量删除用户角色
    @RequiresRoles("root")
    @RequiresPermissions("backManager:deleteAllRole")
    @DeleteMapping("/deleteAllRole")
    public String deleteAllRole(String[] rids, String[] bids,
                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

//        //删除用户
//        for (String id:bids){
//            backManagerService.deleteManager(Integer.parseInt(id));
//        }
//        //删除用户——角色映射
//        for (String id:rids){
//            backManagerService.deleteRoleManager(Integer.parseInt(    id));
//        }

        //删除用户
        backManagerService.deleteAllManager(bids);
        //删除用户——角色映射
        backManagerService.deleteAllRoleManager(rids);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }

    //查询所有权限返回列表页面
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:permission")
    @PostMapping("/permission")
    public Map<String, Object> permission(int curr, int limit,
                                          HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<RolePermission> page = backManagerService.queryPagePermission(curr, limit);

        //总数居条数
        int pageCount = backManagerService.queryPageCountPermission();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //修改角色权限状态
    @RequiresRoles("root")
    @RequiresPermissions("backManager:permissionStatus")
    @PutMapping("/permissionStatus")
    public Map<String, Object> permissionStatus(Integer id, String role, String status, String delete_status,
                                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        BackManager manager = backManagerService.findManagerByAccount(request.getHeader("NAME"));

        Map<String, Object> map = new HashMap<String, Object>();

        if (role.equals("root")) {
            if (!lang)
                map.put("msg", "Permission Denied！");
            else
                map.put("msg", "你没有权限！");
        } else if (manager.getManagerMaster().equals("none")) {
            //前端传来的删除状态 0或1
            Integer deleteStatus = Integer.parseInt(delete_status);
            //根据id修改删除状态和状态
            backManagerService.updatePermissionStatus(id, status, deleteStatus);

            if (!lang)
                map.put("msg", "Successfully changed");
            else
                map.put("msg", "修改成功");
        } else {
            if (!lang)
                map.put("msg", "Permission Denied！");
            else
                map.put("msg", "你没有权限！");
        }
        return map;
    }

    //根据角色名查询所有权限返回列表页面
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("backManager:rolePermission")
    @PostMapping("/rolePermission")
    public Map<String, Object> rolePermission(int curr, int limit, String managerRole,
                                              HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<RolePermission> page = backManagerService.queryPageRolePermission(curr, limit, managerRole);

        //总数居条数
        int pageCount = backManagerService.queryPageCountRolePermission(managerRole);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //添加角色
    @RequiresRoles("root")
    @RequiresPermissions("backManager:addRole")
    @PostMapping("/addRole")
    public Map<String, Object> addRole(String managerRole, String roleDetail,
                                       HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        Map<String, Object> map = new HashMap<String, Object>();
        //向角色表添加角色
        backManagerService.addRole(managerRole, roleDetail);
        //获取添加角色的id
        Role role = backManagerService.findIdByRoleName(managerRole);
        Integer roleId = role.getId();
        //获取所有的权限id
        List<Integer> ids = backManagerService.allPermission();
        for (Integer id : ids) {
            //向角色权限映射表存入角色权限
            backManagerService.addRolePermission(roleId, id);
        }
        if (!lang)
            map.put("msg", "Added successfully");
        else
            map.put("msg", "添加成功");

        return map;
    }

    //修改角色之前
    @RequiresRoles("root")
    @RequiresPermissions("backManager:changeRoleBefore")
    @PutMapping("/changeRoleBefore")
    public Map<String, Object> changeRoleBefore(String managerAccount, String roleName,
                                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        BackManager manager = backManagerService.findManagerByAccount(managerAccount);

        Map<String, Object> map = new HashMap<String, Object>();

        if (manager.getManagerMaster().equals("none")) {
            if (!lang)
                map.put("msg", "Permission Denied！");
            else
                map.put("msg", "你没有权限！");
        } else if (roleName.equals("root")) {
            if (!lang)
                map.put("msg", "Are you sure to change to root? The operation cannot be undone");
            else
                map.put("msg", "您确定更改为root吗？该操作无法撤回");
        }
        return map;
    }

    //修改角色
    @RequiresRoles("root")
    @RequiresPermissions("backManager:changeRole")
    @PutMapping("/changeRole")
    public Map<String, Object> changeRole(String managerAccount, String roleName,
                                          HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        BackManager manager = backManagerService.findManagerByAccount(managerAccount);

        Role role = backManagerService.findIdByRoleName(roleName);

        Map<String, Object> map = new HashMap<String, Object>();

        backManagerService.changeRoleManager(role.getId(), manager.getId());

        if (!lang)
            map.put("msg", "Successfully changed");
        else
            map.put("msg", "修改成功");

        return map;
    }

    //添加权限
    @RequiresRoles("root")
    @RequiresPermissions("backManager:addPer")
    @PostMapping("/addPer")
    public Map<String, Object> addPer(String perUrl, String perName, String roleName,
                                      HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        Map<String, Object> map = new HashMap<String, Object>();
        //向权限表添加权限
        backManagerService.addPermission(perUrl, perName);
        //获取添加角色的id
        Permission permission = backManagerService.findIdByPerUrl(perUrl);
        //获取角色名称的id
        Role role = backManagerService.findIdByRoleName(roleName);
        //插入新角色——权限映射
        backManagerService.addRolePermission(role.getId(), permission.getId());
        if (!lang)
            map.put("msg", "Added successfully");
        else
            map.put("msg", "添加成功");

        return map;
    }

    //删除权限
    @RequiresRoles("root")
    @RequiresPermissions("backManager:deletePer")
    @DeleteMapping("/deletePer")
    public String deletePer(Integer id,
                            HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        //删除权限
        backManagerService.deletePermission(id);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }
}
