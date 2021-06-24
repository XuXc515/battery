package com.xxc.controller;

import com.xxc.authentication.JWTUtil;
import com.xxc.domain.Battery;
import com.xxc.service.ManageService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xxc
 * @date 2020/7/22 - 14:54
 */
@RestController
@RequestMapping("/manage")
public class ManageController extends BaseController {

    @Resource
    private ManageService manageService;

    //添加电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("manage:addBattery")
    @PostMapping("/addBattery")
    public String addBattery(Battery battery,
                             HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        manageService.saveBattery(battery);

        if (!lang)
            return "Added successfully";
        else
            return "添加成功";
    }

    //根据电池id查询电池,在页面回显
    @GetMapping("/findBatteryById")
    public Battery findBatteryById(Integer id,
                                   HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Battery battery = manageService.findBatteryById(id);

        return battery;
    }

    //修改电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("manage:changeBattery")
    @PutMapping("/changeBattery")
    public String updateBattery(Battery battery,
                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        manageService.updateBattery(battery);

        if (!lang)
            return "Successfully changed";
        else
            return "修改成功";
    }


    //删除电池
    @RequiresRoles("root")
    @RequiresPermissions("manage:deleteBattery")
    @DeleteMapping("/deleteBattery")
    public String deleteBattery(Integer id,
                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        manageService.deleteBattery(id);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }

    //删除选中电池
    @RequiresRoles("root")
    @RequiresPermissions("manage:deleteAllBattery")
    @DeleteMapping("/deleteAllBattery")
    public String deleteAllBattery(String[] ids,
                                   HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

//        for (String id:ids){
//            manageService.deleteBattery(Integer.parseInt(id));
//        }
        manageService.deleteAllBattery(ids);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }

    //修改电池金额
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("manage:changeBatteryPrice")
    @PutMapping("/changeBatteryPrice")
    public String updateBatteryPrice(Battery battery,
                                     HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        manageService.saveBatteryPrice(battery);

        if (!lang)
            return "Successfully changed";
        else
            return "修改成功";
    }

}
