package com.xxc.controller;


import com.xxc.authentication.JWTUtil;
import com.xxc.domain.Battery;
import com.xxc.domain.Sale;
import com.xxc.domain.User;
import com.xxc.realm.BackManagerRealm;
import com.xxc.service.AliPayService;
import com.xxc.service.ApplyService;
import com.xxc.service.BatteryService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author xxc
 * @date 2020/7/22 - 10:40
 */
@RestController
@RequestMapping("/apply")
public class ApplyController extends BaseController {

    @Resource
    private ApplyService applyService;

    @Resource
    private BatteryService batteryService;

    @Resource
    private AliPayService aliPayService;

    //查询用户租赁
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("apply:findAll")
    @PostMapping("/findAll")
    public Map<String, Object> findAll(int curr, int limit,
                                       HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Sale> page = applyService.queryPageApply(curr, limit);
        //总数居条数
        int pageCount = applyService.queryPageCountApply();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据日期，电池编号，用户编号查询数据
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("apply:find")
    @PostMapping("/find")
    public Map<String, Object> find(int curr, int limit, String start, String end, String batteryId, String userAccount,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        //变更日期格式
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        Date startDate = df.parse(start);
        Date endDate = df.parse(end);
        df.setLenient(false);

        Map<String, Object> map = new HashMap<String, Object>();
        List<Sale> page = applyService.findByDateApply(curr, limit, startDate, endDate, batteryId, userAccount);

        //总数居条数
        int pageCount = applyService.findByDateApplyCount(startDate, endDate, batteryId, userAccount);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据用户名查询用户,在页面回显
    @GetMapping("/findById")
    public Sale findById(Integer id,
                         HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Sale sale = applyService.findUserByIdApply(id);

        return sale;
    }

    //修改租赁
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("apply:editApply")
    @PutMapping("/editApply")
    public String updateApply(Integer id, String batteryId,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        Battery battery = batteryService.findBatteryByDeviceId(batteryId);
        Sale sale = applyService.findSaleByDeviceId(batteryId);

        if (battery == null)
            if (!lang)
                return "Battery is not present";
            else
                return "电池不存在";
        else if (sale == null) {
            applyService.updateApply(id, batteryId);
            if (!lang)
                return "Successfully changed";
            else
                return "修改成功";
        } else if (!lang)
            return "The battery has been leased";
        else
            return "电池已租借";
    }

    //添加租赁
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("apply:addApply")
    @PostMapping("/addApply")
    public String addApply(Sale sale,
                           HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        //设置订单id
        Sale saleLast = aliPayService.findLastSaleId();
        Integer saleId = Integer.parseInt(saleLast.getSaleId()) + 1;
        sale.setSaleId(saleId.toString());

        //设置租赁状态
        if (sale.getStatus().equals("已支付"))
            sale.setRentalStatus(1);
        else
            sale.setRentalStatus(0);

        //根据电池id查询电池是否存在
        Sale findSaleByDeviceId = applyService.findSaleByBatteryId(sale.getBatteryId());
        if (findSaleByDeviceId == null) {
            //设置电池价格
            Battery battery = batteryService.findBatteryInfoById(sale.getBatteryId());
            if (battery == null)
                if (!lang)
                    return "BatteryId does not exist";
                else
                    return "电池编号不存在";
            else {
                sale.setPrice(battery.getBatteryPrice());
                aliPayService.saveBatteryOrder(sale);
                if (!lang)
                    return "Added successfully";
                else
                    return "添加成功";
            }
        } else if (!lang)
            return "The battery has been rented out";
        else
            return "电池已租出";
    }

    //删除订单的信息
    @RequiresRoles("root")
    @RequiresPermissions("apply:deleteApply")
    @DeleteMapping("/deleteApply")
    public String deleteApply(Integer id,
                              HttpServletRequest request, HttpServletResponse response) {

        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        applyService.deleteApply(id);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }


}
