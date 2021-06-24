package com.xxc.controller;

import com.xxc.authentication.JWTFilter;
import com.xxc.authentication.JWTUtil;
import com.xxc.domain.Echarts;
import com.xxc.service.BatteryService;
import com.xxc.service.CostService;
import com.xxc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xxc
 * @date 2020/7/21 - 14:13
 */
@Controller
public class IndexController extends BaseController {

    @Resource
    private UserService userService;

    @Resource
    private CostService costService;

    @Resource
    private BatteryService batteryService;

    @RequestMapping(value = {"/", "/login"})
    public void index(HttpServletResponse response) throws IOException {

        response.sendRedirect("/static/login.html");

    }

    //来到主页面
    @GetMapping("/home")
    @ResponseBody
    public List<Echarts> toHome(HttpServletRequest request, HttpServletResponse response) {

        // 生成 Token
        String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
        response.setHeader("TOKEN", token);
        boolean lang = request.getHeader("LANG").equals("ch");

        //累计租户数
        Integer TenantCount = userService.userCount();
        //激活租户数
        Integer activeTenantCount = userService.userActiveCount();
        //欠款租户数
        Integer arrearsTenantCount = userService.userArrearsCount();
        //电池累积租出数
        Integer batteryRentalCount = costService.batteryRentalCount();
        //当前电池租出数
        Integer batteryCurrentRentalCount = costService.batteryCurrentRentalCount();
        //电池总数
        Integer batteryCount = batteryService.batteryCount();

        List<Echarts> echarts = new ArrayList<>();

        if (!lang) {

            Echarts echart1 = new Echarts("Cumulative number of tenants", TenantCount);
            Echarts echart2 = new Echarts("Activated number of tenants", activeTenantCount);
            Echarts echart3 = new Echarts("Arrears number of tenants", arrearsTenantCount);
            Echarts echart4 = new Echarts("Cumulative battery lease", batteryRentalCount);
            Echarts echart5 = new Echarts("Current battery lease", batteryCurrentRentalCount);
            Echarts echart6 = new Echarts("Total number of batteries", batteryCount);

            echarts.add(echart1);
            echarts.add(echart2);
            echarts.add(echart3);
            echarts.add(echart4);
            echarts.add(echart5);
            echarts.add(echart6);
        } else {
            Echarts echart1 = new Echarts("累计租户数", TenantCount);
            Echarts echart2 = new Echarts("激活租户数", activeTenantCount);
            Echarts echart3 = new Echarts("欠款租户数", arrearsTenantCount);
            Echarts echart4 = new Echarts("电池累积租赁数", batteryRentalCount);
            Echarts echart5 = new Echarts("当前电池租赁数", batteryCurrentRentalCount);
            Echarts echart6 = new Echarts("电池总数", batteryCount);

            echarts.add(echart1);
            echarts.add(echart2);
            echarts.add(echart3);
            echarts.add(echart4);
            echarts.add(echart5);
            echarts.add(echart6);
        }

        return echarts;
    }

}
