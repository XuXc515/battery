package com.xxc.controller;


import com.xxc.authentication.JWTUtil;
import com.xxc.domain.Battery;
import com.xxc.service.BatteryService;
import com.xxc.service.MonitorService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xxc
 * @date 2020/7/22 - 11:55
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    @Resource
    private MonitorService monitorService;

    @Resource
    private BatteryService batteryService;

    //分页获取所有电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("monitor:location")
    @PostMapping("/location")
    public Map<String, Object> batteryLocation(int curr, int limit,
                                               HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Battery> page = monitorService.queryPageBattery(curr, limit);

        //总数居条数
        int pageCount = monitorService.queryPageCountBattery();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据电池编号查询电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("monitor:findBattery")
    @PostMapping("/findBattery")
    public Map<String, Object> findBatteryLocation(int curr, int limit, String batteryId,
                                                   HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<>();
        List<Battery> page = monitorService.queryPageFindBattery(curr, limit, batteryId);

        //总数居条数
        int pageCount = monitorService.queryPageCountFindBattery(batteryId);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //查询电池总数，在线电池，离线电池，报错电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("monitor:batteryValue")
    @GetMapping("/batteryValue")
    public Map<String, Object> batteryValue(HttpServletRequest request, HttpServletResponse response) {

        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();

        //所有电池
        Integer batteryCount = batteryService.batteryCount();
        //在线电池
        Integer batteryOnLineCount = batteryService.batteryOnLineCount();
        //离线电池
        Integer batteryNoLineCount = batteryService.batteryNoLineCount();
        //休眠电池
        Integer batterySleepCount = batteryService.batterySleepCount();
        //报错电池
        Integer batteryErrorCount = batteryService.batteryErrorCount();

        map.put("batteryCount", batteryCount);
        map.put("batteryOnLineCount", batteryOnLineCount);
        map.put("batteryNoLineCount", batteryNoLineCount);
        map.put("batterySleepCount", batterySleepCount);
        map.put("batteryErrorCount", batteryErrorCount);

        return map;
    }
}
