package com.xxc.realm;

import com.xxc.authentication.JWTToken;
import com.xxc.authentication.JWTUtil;
import com.xxc.dao.PermissionMapper;
import com.xxc.dao.RoleMapper;
import com.xxc.dao.UserMapper;
import com.xxc.domain.BackManager;
import com.xxc.domain.Permission;
import com.xxc.domain.Role;
import com.xxc.domain.User;
import com.xxc.service.BackManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author xxc
 * @date 2020/7/28 - 10:53
 */
public class BackManagerRealm extends AuthorizingRealm {

    @Resource
    private BackManagerService backManagerService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {

        //获取当前用户账户名称
        String account = JWTUtil.getUsername(token.toString());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //判断用户名是否是管理员用户
        BackManager manager = backManagerService.findManagerByAccount(account);
        if (manager == null) {
            //不是则为普通用户
            Set<String> roleSet = new HashSet<String>();
            User user = userMapper.findBySign(account);
            String role = user.getUserRole();
            roleSet.add(role);
            //存入角色
            info.setRoles(roleSet);

            //拿到该用户的权限集
            List<Permission> permissions = permissionMapper.findUserPermission(role);
            Set<String> permissionSet = new HashSet<String>();
            for (Permission p : permissions) {
                //存入Set集合
                permissionSet.add(p.getPerName());
            }
            //存入权限
            info.setStringPermissions(permissionSet);
            return info;

        } else {
            //拿到该用户的角色存入Set
            Set<String> roleSet = new HashSet<String>();
            //判断管理员的角色是否为停用状态，true就没有角色
            String status = manager.getManagerStatus();
            if (status.equals("Unused")) {
                roleSet.add(null);
            } else {
                List<Role> roles = roleMapper.findRoleByAccount(account);
                for (Role r : roles) {
                    //存入Set集合
                    roleSet.add(r.getManagerRole());
                }
            }
            info.setRoles(roleSet);
            //拿到该用户的权限集
            List<Permission> permissions = permissionMapper.findPermissionByAccount(account);
            Set<String> permissionSet = new HashSet<String>();
            for (Permission p : permissions) {
                //存入Set集合
                permissionSet.add(p.getPerName());
            }
            //存入权限
            info.setStringPermissions(permissionSet);
            return info;
        }
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtil.getUsername(token);

        if (StringUtils.isBlank(username)) {
            throw new AuthenticationException("认证失败，请重新登录");
        } else {
            // 通过用户名查询用户信息
            BackManager manager = backManagerService.findManagerByAccount(username);
            if (manager == null) {
                User user = userMapper.findBySign(username);
                if (user == null)
                    throw new AuthenticationException("认证失败,请重新登录");
                else {
                    String userSecret = user.getUserPassword();
                    if (!JWTUtil.verify(token, username, userSecret)) {
                        throw new AuthenticationException("认证失败,请重新登录");
                    } else {
                        String realmName = getName();
                        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, realmName);
                        return info;
                    }
                }
            } else {
                String backManagerSecret = manager.getManagerPassword();
                if (!JWTUtil.verify(token, username, backManagerSecret))
                    throw new AuthenticationException("认证失败,请重新登录");
                else {
                    String realmName = getName();
                    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(token, token, realmName);
                    return info;
                }
            }
        }
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


}
