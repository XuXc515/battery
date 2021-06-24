package com.xxc.controller;

import com.xxc.authentication.JWTUtil;
import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import com.xxc.domain.User;
import com.xxc.service.CostService;
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
 * @date 2020/7/22 - 11:04
 */
@RestController
@RequestMapping("/cost")
public class CostController extends BaseController {

    @Resource
    private CostService costService;

    //查询用户账户信息
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("cost:tenant")
    @PostMapping("/tenant")
    public Map<String, Object> findAll(int curr, int limit,
                                       HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Sale> page = costService.queryPageCost(curr, limit);

        //总数居条数
        int pageCount = costService.queryPageCountCost();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据订单编号，用户名字查询数据
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("cost:findTenant")
    @PostMapping("/findTenant")
    public Map<String, Object> findTenant(int curr, int limit, String saleId, String userName,
                                          HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Sale> page = costService.findBySaleIdAndNameCost(curr, limit, saleId, userName);

        //总数居条数
        int pageCount = costService.findBySaleIdAndNameCostCount(saleId, userName);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //分页查询用户充值记录
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("cost:recharge")
    @PostMapping("/recharge")
    public Map<String, Object> findAllRecharge(int curr, int limit,
                                               HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Recharge> page = costService.queryPageRecharge(curr, limit);

        //总数居条数
        int pageCount = costService.queryPageCountRecharge();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据日期，用户账户查询数据
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("cost:findRecharge")
    @PostMapping("/findRecharge")
    public Map<String, Object> findRecharge(int curr, int limit, String start, String end, String userAccount,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        //变更日期格式
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
        Date startDate = df.parse(start);
        Date endDate = df.parse(end);
        df.setLenient(false);

        Map<String, Object> map = new HashMap<String, Object>();
        List<Recharge> page = costService.findByDateRecharge(curr, limit, startDate, endDate, userAccount);

        //总数居条数
        int pageCount = costService.findByDateRechargeCount(startDate, endDate, userAccount);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //分页查询全部欠款用户
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("cost:arrears")
    @PostMapping("/arrears")
    public Map<String, Object> findAllArrears(int curr, int limit,
                                              HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<User> page = costService.queryPageArrearsUser(curr, limit);

        //总数居条数
        int pageCount = costService.queryPageCountArrearsUser();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据用户编号，用户账户查询欠款用户
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("cost:findArrears")
    @PostMapping("/findArrears")
    public Map<String, Object> findArrears(int curr, int limit, String userId, String userAccount,
                                           HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<User> page = costService.findArrearsUserByUserIdAndAccount(curr, limit, userId, userAccount);

        //总数居条数
        int pageCount = costService.findArrearsUserByUserIdAndAccountCount(userId, userAccount);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据用户名查询用户,在页面回显
    @GetMapping("/findArrearsById")
    public User findArrearsById(Integer id,
                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        User user = costService.findArrearsById(id);

        return user;
    }

    //修改欠款
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("cost:editArrears")
    @PutMapping("/editArrears")
    public String updateArrears(Integer id, String balance,
                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        costService.updateArrears(id, balance);

        if (!lang)
            return "Successfully changed";
        else
            return "修改成功";
    }

    //删除欠款的信息
    @RequiresRoles("root")
    @RequiresPermissions("cost:deleteArrears")
    @DeleteMapping("/deleteArrears")
    public String deleteArrears(Integer id,
                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        costService.deleteArrears(id);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }

}
