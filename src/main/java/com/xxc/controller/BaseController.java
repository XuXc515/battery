package com.xxc.controller;

import com.xxc.authentication.JWTUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.UnauthorizedException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xxc
 * @date 2020/8/3 - 12:50
 */
@RestControllerAdvice
public class BaseController {
    /**
     * 权限异常
     */
    @ExceptionHandler
    public Map<String, Object> exception(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<String, Object>();

        if (ex instanceof UnauthorizedException) {
            // 生成 Token
            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
            response.setHeader("TOKEN", token);
            boolean lang = request.getHeader("LANG").equals("ch");

            if (!lang)
                map.put("msg", "Permission Denied！");
            else
                map.put("msg", "你没有权限！");

            return map;
        } else if (ex instanceof UnsupportedTokenException) {
            // 生成 Token
            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
            response.setHeader("TOKEN", token);
            map.put("msg", "不正确的token");
            System.out.println("不正确的token");
            return map;
        } else if (ex instanceof UnknownAccountException) {
            // 生成 Token
            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
            response.setHeader("TOKEN", token);
            map.put("msg", "用户名为空，token校验不通过");
            System.out.println("用户名为空，token校验不通过");
            return map;
        }
        return null;
    }
}
