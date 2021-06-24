package com.xxc.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.xxc.authentication.JWTUtil;

import com.xxc.config.BatteryConfig;
import com.xxc.config.WebSocketConfig;
import com.xxc.domain.*;
import com.xxc.service.BatteryService;
import com.xxc.util.TableStringUtil;
import com.xxc.util.ThreadUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.java_websocket.client.WebSocketClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xxc
 * @date 2020/7/22 - 11:31
 */
@RestController
@RequestMapping("/battery")
public class BatteryController extends BaseController {

    @Resource
    private BatteryService batteryService;

    private static String URL = "http://8.129.79.195:8099";

    private static String WSURI = "ws://8.129.79.195:8099/imserver/";

    //查询所有电池信息
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:battery")
    @PostMapping("/battery")
    public Map<String, Object> battery(int curr, int limit,
                                       HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Battery> page = batteryService.queryPageBattery(curr, limit);

        //总数居条数
        int pageCount = batteryService.queryPageCountBattery();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据电池编号查询电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:searchBattery")
    @PostMapping("/searchBattery")
    public Map<String, Object> searchBattery(int curr, int limit, String batteryId,
                                             HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Battery> page = batteryService.queryPageFindBattery(curr, limit, batteryId);

        //总数居条数
        int pageCount = batteryService.queryPageCountFindBattery(batteryId);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }


    //查询所有电池状态信息
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:batteryType")
    @PostMapping("/batteryType")
    public Map<String, Object> batteryType(int curr, int limit,
                                           HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<BatteryType> page = batteryService.queryPageBatteryType(curr, limit);

        //总数居条数
        int pageCount = batteryService.queryPageCountBatteryType();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据状态编号查询状态
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:searchBatteryType")
    @PostMapping("/searchBatteryType")
    public Map<String, Object> searchBatteryType(int curr, int limit, String typeId,
                                                 HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<BatteryType> page = batteryService.queryPageFindBatteryType(curr, limit, typeId);

        //总数居条数
        int pageCount = batteryService.queryPageCountFindBatteryType(typeId);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }


    //查询所有电池状态信息
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:batteryStatus")
    @PostMapping("/batteryStatus")
    public Map<String, Object> batteryStatus(int curr, int limit,
                                             HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<BatteryStatus> page = batteryService.queryPageBatteryStatus(curr, limit);

        //总数居条数
        int pageCount = batteryService.queryPageCountBatteryStatus();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据状态编号查询状态
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:searchBatteryStatus")
    @PostMapping("/searchBatteryStatus")
    public Map<String, Object> searchBatteryStatus(int curr, int limit, String statusId,
                                                   HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<BatteryStatus> page = batteryService.queryPageFindBatteryStatus(curr, limit, statusId);

        //总数居条数
        int pageCount = batteryService.queryPageCountFindBatteryStatus(statusId);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }


    //查询所有未填写电池金额信息
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:batteryPrice")
    @PostMapping("/batteryPrice")
    public Map<String, Object> batteryPrice(int curr, int limit,
                                            HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Battery> page = batteryService.queryPageBatteryPrice(curr, limit);

        //总数居条数
        int pageCount = batteryService.queryPageCountBatteryPrice();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //查询所有电池维修中状态信息
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:batteryRepair")
    @PostMapping("/batteryRepair")
    public Map<String, Object> batteryRepair(int curr, int limit,
                                             HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Battery> page = batteryService.queryPageBatteryRepair(curr, limit);

        //总数居条数
        int pageCount = batteryService.queryPageCountBatteryRepair();
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //根据电池编号查询维修电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:findBatteryRepair")
    @PostMapping("/findBatteryRepair")
    public Map<String, Object> findBatteryRepair(int curr, int limit, String batteryId,
                                                 HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        List<Battery> page = batteryService.queryFindPageBatteryRepair(curr, limit, batteryId);

        //总数居条数
        int pageCount = batteryService.queryFindPageCountBatteryRepair(batteryId);
        map.put("data", page);
        map.put("ct", pageCount);

        return map;
    }

    //添加维修电池
    @RequiresRoles(value = {"root", "admin"}, logical = Logical.OR)
    @RequiresPermissions("battery:addBatteryRepair")
    @PostMapping("/addBatteryRepair")
    public String addBatteryRepair(String deviceId, String faultDetail,
                                   HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        Battery battery = batteryService.findBatteryByDeviceId(deviceId);

        if (battery != null) {
            if (!battery.getDeviceRepair().equals("维修中")) {
                batteryService.addBatteryRepair(deviceId, faultDetail);
                if (!lang)
                    return "Added successfully";
                else
                    return "添加成功";
            } else if (!lang)
                return "Battery failure has been reported";
            else
                return "电池故障已上报";
        } else {
            if (!lang)
                return "Battery is not present";
            else
                return "电池不存在";
        }

    }

    //删除电池状态
    @RequiresRoles("root")
    @RequiresPermissions("battery:changeBatteryRepair")
    @PostMapping("/changeBatteryRepair")
    public String changeBatteryRepair(Integer id,
                                      HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

        batteryService.deleteBatteryRepair(id);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }

    //删除选中电池维修中
    @RequiresRoles("root")
    @RequiresPermissions("battery:changeAllBatteryRepair")
    @PostMapping("/changeAllBatteryRepair")
    public String changeAllBatteryRepair(String[] ids,
                                         HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
        boolean lang = request.getHeader("LANG").equals("ch");

//        for (String id:ids){
//            batteryService.deleteBatteryRepair(Integer.parseInt(id));
//        }
        batteryService.deleteAllBatteryRepair(ids);

        if (!lang)
            return "successfully deleted";
        else
            return "删除成功";
    }

    //分页查询电池
    @PostMapping("/batteryInfo")
    public Map<String, Object> users(int curr, int limit,
                                     HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();

        List<JSONObject> jsonList = new ArrayList<JSONObject>();

        List<Battery> page = batteryService.queryPageBatteryInfo(curr, limit);
        for (Battery i : page){
            JSONObject data = JSONUtil.createObj()
                    .set("deviceId", i.getDeviceId())
                    .set("getDevSoc", i.getBatteryType().getBatterySoc())
                    .set("getDevTemp", i.getBatteryStatus().getBatteryTemperature())
                    .set("getDevGps", i.getAddress())
                    .set("getUseDay", i.getBatteryTime().getUsedDay())
                    .set("getUseHour", i.getBatteryTime().getUsedHour())
                    .set("getUseMin", i.getBatteryTime().getUsedMin())
                    .set("getUseSec", i.getBatteryTime().getUsedSec())
                    .set("getUseTime", i.getBatteryTime().getUseTotalTime())
                    .set("getSetUseTime", i.getBatteryTime().getUsedTotalTime())
                    .set("getDevElect", i.getBatteryStatus().getBatteryStatus());
            jsonList.add(data);
        }

        //总数居条数
        int pageCount = batteryService.queryPageCountBatteryInfo();
        map.put("data", jsonList);
        map.put("ct", pageCount);

        return map;
    }

    //开启电池
    @PostMapping("/onBattery")
    public String onBattery(@RequestBody com.alibaba.fastjson.JSONObject jsonObject,
                            HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        String jsonString = jsonObject.toString();

        String[] batteryString = BatteryConfig.BATTERYSTRING;
        JSONObject json = JSONUtil.createObj();

        HashMap map = JSON.parseObject(jsonString, HashMap.class);

        String deviceId = null;

        for (Object key : map.keySet()) {
            for (int i = 0; i < batteryString.length; i++) {
                if (key == batteryString[i])
                    json.set(key.toString(), map.get(key));
                else if (key == "deviceId") {
                    deviceId = map.get(key).toString();
                    json.set(key.toString(), map.get(key));
                }
            }
        }

        json.set("status", "set");

        JSONObject before = JSONUtil.createObj()
                .set("getDevElect", "")
                .set("status", "get")
                .set("deviceId", deviceId);

        String uri = WSURI + deviceId;

        //获取websocketClient对象
        WebSocketConfig webSocket = new WebSocketConfig();
        WebSocketClient beWsConn = webSocket.getConn(uri, before.toString());

        ThreadUtil threadUtil = new ThreadUtil();

        Map<String, String> be_result = threadUtil.ThreadSleep();

//        Thread.sleep(1000);
//        be_result = WebSocketConfig.jsonMaps;
//        if (be_result.size() == 0 || be_result == null) {
//            Thread.sleep(500);
//            be_result = WebSocketConfig.jsonMaps;
//            if (be_result.size() == 0 || be_result == null) {
//                Thread.sleep(500);
//                be_result = WebSocketConfig.jsonMaps;
//                if (be_result.size() == 0 || be_result == null) {
//                    Thread.sleep(2000);
//                    be_result = WebSocketConfig.jsonMaps;
//                    if (be_result.size() == 0 || be_result == null) {
//                        Thread.sleep(2000);
//                        be_result = WebSocketConfig.jsonMaps;
//                        if (be_result.size() == 0 || be_result == null) {
//                            Thread.sleep(3000);
//                            be_result = WebSocketConfig.jsonMaps;
//                            if (be_result.size() == 0 || be_result == null) {
//                                Thread.sleep(3000);
//                                be_result = WebSocketConfig.jsonMaps;
//                                if (be_result.size() == 0 || be_result == null) {
//                                    Thread.sleep(10000);
//                                    be_result = WebSocketConfig.jsonMaps;
//                                    if (be_result.size() == 0 || be_result == null) {
//                                        be_result.put("status", "wait");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
        JSONObject be_data = new JSONObject(be_result);
        WebSocketConfig.jsonMaps.clear();
        beWsConn.close();

        TableStringUtil util = new TableStringUtil();

        if (be_data.get("status").equals("wait")){
            return be_data.toString();
        }
        else {
            if (be_data.get("status").equals("device_no_connect")) {
                return be_data.toString();
            } else {
                if (be_data.get("getDevElect").equals("租期过期")) {
                    be_data.set("deviceId", deviceId);
                    boolean batteryStatus = util.table_battery_status(be_data.toString());
                    if (batteryStatus)
                        return be_data.toString();
                    else{
                        JSONObject res = JSONUtil.createObj()
                                .set("status", "wait");
                        return res.toString();
                    }
                } else {

                    if (be_data.get("getDevElect").equals("开启")) {
                        be_data.set("deviceId", deviceId);
                        boolean batteryStatus = util.table_battery_status(be_data.toString());
                        if (batteryStatus)
                            return be_data.toString();
                        else {
                            JSONObject res = JSONUtil.createObj()
                                    .set("status", "wait");
                            return res.toString();
                        }
                    } else {
                        //获取websocketClient对象
                        WebSocketClient wsConn = webSocket.getConn(uri, json.toString());

                        Map<String, String> result = threadUtil.ThreadSleep();

//                        Thread.sleep(1000);
//                        result = WebSocketConfig.jsonMaps;
//                        if (result.size() == 0 || result == null) {
//                            Thread.sleep(500);
//                            result = WebSocketConfig.jsonMaps;
//                            if (result.size() == 0 || result == null) {
//                                Thread.sleep(500);
//                                result = WebSocketConfig.jsonMaps;
//                                if (result.size() == 0 || result == null) {
//                                    Thread.sleep(2000);
//                                    result = WebSocketConfig.jsonMaps;
//                                    if (result.size() == 0 || result == null) {
//                                        Thread.sleep(2000);
//                                        result = WebSocketConfig.jsonMaps;
//                                        if (result.size() == 0 || result == null) {
//                                            result.put("status", "wait");
//                                        }
//                                    }
//                                }
//                            }
//                        }
                        JSONObject data = new JSONObject(result);
                        WebSocketConfig.jsonMaps.clear();

                        wsConn.close();

                        if (data.get("status").equals("wait")) {
                            return data.toString();
                        } else {
                            JSONObject status = new JSONObject()
                                    .set("deviceId", deviceId)
                                    .set("getDevElect", "开启");

                            boolean batteryStatus = util.table_battery_status(status.toString());

                            if (batteryStatus)
                                return data.toString();
                            else {
                                JSONObject res = JSONUtil.createObj()
                                        .set("status", "wait");
                                return res.toString();
                            }
                        }
                    }
                }
            }
        }
    }

    //关闭电池
    @PostMapping("/offBattery")
    public String offBattery(@RequestBody com.alibaba.fastjson.JSONObject jsonObject,
                             HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        String jsonString = jsonObject.toString();

        String[] batteryString = BatteryConfig.BATTERYSTRING;
        JSONObject json = JSONUtil.createObj();

        HashMap map = JSON.parseObject(jsonString, HashMap.class);

        String deviceId = null;

        for (Object key : map.keySet()) {
            for (int i = 0; i < batteryString.length; i++) {
                if (key == batteryString[i])
                    json.set(key.toString(), map.get(key));
                else if (key == "deviceId") {
                    deviceId = map.get(key).toString();
                    json.set(key.toString(), map.get(key));
                }
            }
        }

        json.set("status", "set");

        JSONObject before = JSONUtil.createObj()
                .set("getDevElect", "")
                .set("status", "get")
                .set("deviceId", deviceId);

        String uri = WSURI + deviceId;

        //获取websocketClient对象
        WebSocketConfig webSocket = new WebSocketConfig();
        WebSocketClient beWsConn = webSocket.getConn(uri, before.toString());

        ThreadUtil threadUtil = new ThreadUtil();

        Map<String, String> be_result = threadUtil.ThreadSleep();

//        Thread.sleep(1000);
//        be_result = WebSocketConfig.jsonMaps;
//        if (be_result.size() == 0 || be_result == null) {
//            Thread.sleep(500);
//            be_result = WebSocketConfig.jsonMaps;
//            if (be_result.size() == 0 || be_result == null) {
//                Thread.sleep(500);
//                be_result = WebSocketConfig.jsonMaps;
//                if (be_result.size() == 0 || be_result == null) {
//                    Thread.sleep(2000);
//                    be_result = WebSocketConfig.jsonMaps;
//                    if (be_result.size() == 0 || be_result == null) {
//                        Thread.sleep(2000);
//                        be_result = WebSocketConfig.jsonMaps;
//                        if (be_result.size() == 0 || be_result == null) {
//                            Thread.sleep(3000);
//                            be_result = WebSocketConfig.jsonMaps;
//                            if (be_result.size() == 0 || be_result == null) {
//                                Thread.sleep(3000);
//                                be_result = WebSocketConfig.jsonMaps;
//                                if (be_result.size() == 0 || be_result == null) {
//                                    Thread.sleep(10000);
//                                    be_result = WebSocketConfig.jsonMaps;
//                                    if (be_result.size() == 0 || be_result == null) {
//                                        be_result.put("status", "wait");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

        JSONObject be_data = new JSONObject(be_result);
        WebSocketConfig.jsonMaps.clear();
        beWsConn.close();

        TableStringUtil util = new TableStringUtil();

        if (be_data.get("status").equals("wait")){
            return be_data.toString();
        }
        else {
            if (be_data.get("status").equals("device_no_connect")) {
                return be_data.toString();
            } else {
                if (be_data.get("getDevElect").equals("租期过期")) {
                    be_data.set("deviceId", deviceId);
                    boolean batteryStatus = util.table_battery_status(be_data.toString());
                    if (batteryStatus)
                        return be_data.toString();
                    else{
                        JSONObject res = JSONUtil.createObj()
                                .set("status", "wait");
                        return res.toString();
                    }
                } else {
                    if (be_data.get("getDevElect").equals("关闭")) {
                        be_data.set("deviceId", deviceId);
                        boolean batteryStatus = util.table_battery_status(be_data.toString());
                        if (batteryStatus)
                            return be_data.toString();
                        else{
                            JSONObject res = JSONUtil.createObj()
                                    .set("status", "wait");
                            return res.toString();
                        }
                    } else {
                        //获取websocketClient对象
                        WebSocketClient wsConn = webSocket.getConn(uri, json.toString());

                        Map<String, String> result = threadUtil.ThreadSleep();

//                        Thread.sleep(1000);
//                        result = WebSocketConfig.jsonMaps;
//                        if (result.size() == 0 || result == null) {
//                            Thread.sleep(500);
//                            result = WebSocketConfig.jsonMaps;
//                            if (result.size() == 0 || result == null) {
//                                Thread.sleep(500);
//                                result = WebSocketConfig.jsonMaps;
//                                if (result.size() == 0 || result == null) {
//                                    Thread.sleep(2000);
//                                    result = WebSocketConfig.jsonMaps;
//                                    if (result.size() == 0 || result == null) {
//                                        Thread.sleep(2000);
//                                        result = WebSocketConfig.jsonMaps;
//                                        if (result.size() == 0 || result == null) {
//                                            result.put("status", "wait");
//                                        }
//                                    }
//                                }
//                            }
//                        }
                        JSONObject data = new JSONObject(result);
                        WebSocketConfig.jsonMaps.clear();
                        wsConn.close();

                        if (data.get("status").equals("wait")){
                            return data.toString();
                        }
                        else {
                            JSONObject status = new JSONObject()
                                    .set("deviceId", deviceId)
                                    .set("getDevElect", "关闭");

                            boolean batteryStatus = util.table_battery_status(status.toString());

                            if (batteryStatus)
                                return data.toString();
                            else {
                                JSONObject res = JSONUtil.createObj()
                                        .set("status", "wait");
                                return res.toString();
                            }

                        }
                    }
                }
            }
        }
    }


    //websocket发送获取字符请求
    @PostMapping("/getBatteryTimeWS")
    public Map<String, Object> getBatteryTimeWebSocket(@RequestBody com.alibaba.fastjson.JSONObject jsonObject,
                                          HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> dataMap = new HashMap<String, Object>();

        //获取前端传过来的数据
        String jsonString = jsonObject.toString();

        //BatteryConfig.BATTERYSTRING是包含所有字符集的数组
        String[] batteryString = BatteryConfig.BATTERYSTRING;

        JSONObject json = JSONUtil.createObj();

        JSONObject jsonGps = JSONUtil.createObj();

        HashMap map = JSON.parseObject(jsonString, HashMap.class);

        String deviceId = null;

        //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
        for (Object key : map.keySet()) {
            for (int i = 0; i < batteryString.length; i++) {
                if (key == batteryString[i]) {
                    if (key == "getDevGps"){
                        jsonGps.set(key.toString(), map.get(key));
                    }
                    else
                        json.set(key.toString(), map.get(key));
                }
                else if (key == "deviceId") {
                    deviceId = map.get(key).toString();
                    json.set(key.toString(), map.get(key));
                    jsonGps.set(key.toString(), map.get(key));
                }
            }
        }

        json.set("status", "get");
        jsonGps.set("status", "get");

        JSONObject before = JSONUtil.createObj()
                .set("getDevElect", "")
                .set("status", "get")
                .set("deviceId", deviceId);

        String uri = WSURI + deviceId;

        //获取websocketClient对象
        WebSocketConfig webSocket = new WebSocketConfig();
        WebSocketClient beWsConn = webSocket.getConn(uri, before.toString());

        ThreadUtil threadUtil = new ThreadUtil();

        Map<String, String> be_result = threadUtil.ThreadSleep();

        JSONObject be_data = new JSONObject(be_result);
        WebSocketConfig.jsonMaps.clear();
        beWsConn.close();

        TableStringUtil util = new TableStringUtil();

        if (be_data.get("status").equals("wait")){
            dataMap.put("data", be_data);

        }
        else {
            if (be_data.get("status").equals("device_no_connect")) {
                dataMap.put("data", be_data);

            } else {
                if (be_data.get("getDevElect").equals("租期过期")) {
                    be_data.set("deviceId", deviceId);

                    boolean batteryStatus = util.table_battery_status(be_data.toString());

                    if (batteryStatus) {
                        List<JSONObject> jsonList = new ArrayList<JSONObject>();
                        be_data.set("getDevSoc", "0");
                        be_data.set("getDevTemp", "0");
                        be_data.set("getDevGps", "0");
                        be_data.set("getUseDay", "0");
                        be_data.set("getUseHour", "0");
                        be_data.set("getUseMin", "0");
                        be_data.set("getUseSec", "0");
                        be_data.set("getUseTime", "0");
                        be_data.set("getSetUseTime", "0");
                        jsonList.add(be_data);
                        dataMap.put("data", jsonList);
                    }
                    else{
                        JSONObject res = JSONUtil.createObj()
                                .set("status", "wait");
                        dataMap.put("data", res);
                    }
                } else {
                    if (be_data.get("getDevElect").equals("关闭")) {
                        be_data.set("deviceId", deviceId);
                        boolean batteryStatus = util.table_battery_status(be_data.toString());
                        if (batteryStatus) {
                            List<JSONObject> jsonList = new ArrayList<JSONObject>();
                            be_data.set("getDevSoc", "0");
                            be_data.set("getDevTemp", "0");
                            be_data.set("getDevGps", "0");
                            be_data.set("getUseDay", "0");
                            be_data.set("getUseHour", "0");
                            be_data.set("getUseMin", "0");
                            be_data.set("getUseSec", "0");
                            be_data.set("getUseTime", "0");
                            be_data.set("getSetUseTime", "0");
                            jsonList.add(be_data);
                            dataMap.put("data", jsonList);
                        }
                        else{
                            JSONObject res = JSONUtil.createObj()
                                    .set("status", "wait");
                            dataMap.put("data", res);
                        }
                    } else {
                        //获取websocketClient对象
                        WebSocketClient wsConn = webSocket.getConn(uri, json.toString());

                        Map<String, String> result = threadUtil.ThreadSleep();


                        JSONObject data_result = new JSONObject(result);

                        WebSocketConfig.jsonMaps.clear();
                        wsConn.close();

                        //获取websocketClient对象
                        WebSocketClient gpsConn = webSocket.getConn(uri, jsonGps.toString());

                        Map<String, String> resultGps = threadUtil.ThreadSleep();


                        JSONObject data_resultGps = new JSONObject(resultGps);

                        WebSocketConfig.jsonMaps.clear();

                        gpsConn.close();

                        JSONObject data = new JSONObject();

                        if (data_result.get("status").equals("wait") || data_resultGps.get("status").equals("wait"))
                            data.set("status", "wait");
                        else{
                            data.putAll(data_result);
                            data.putAll(data_resultGps);
                            data.set("getDevElect", "开启");
                            data.set("deviceId", deviceId);



                            boolean battery = util.table_battery(data.toString());
                            boolean batteryType = util.table_battery_type(data.toString());
                            boolean batteryStatus = util.table_battery_status(data.toString());
                            boolean batteryTime = util.table_battery_time(data.toString());

                            List<JSONObject> jsonList = new ArrayList<JSONObject>();

                            if (!battery || !batteryType || !batteryStatus || !batteryTime) {
                                data.set("status", "wait");
                            }
                            jsonList.add(data);
                            dataMap.put("data", jsonList);
                        }
                    }
                }
            }
        }
        return dataMap;
    }

    //websocket发送设置字符请求
    @PostMapping("/setBatteryTimeWS")
    public String setBatteryTimeWS(@RequestBody com.alibaba.fastjson.JSONObject jsonObject,
                                   HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        //获取前端传过来的数据
        String jsonString = jsonObject.toString();

        //BatteryConfig.BATTERYSTRING是包含所有字符集的数组
        String[] batteryString = BatteryConfig.BATTERYSTRING;

        JSONObject json = JSONUtil.createObj();

        HashMap map = JSON.parseObject(jsonString, HashMap.class);

        String deviceId = null;

        //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
        for (Object key : map.keySet()) {
            for (int i = 0; i < batteryString.length; i++) {
                if (key == batteryString[i])
                    if (map.get(key).equals(""))
                        json.set(key.toString(), "0");
                    else
                        json.set(key.toString(), map.get(key));
                else if (key == "deviceId") {
                    deviceId = map.get(key).toString();
                    json.set(key.toString(), map.get(key));
                }
            }
        }

        json.set("status", "set");

        String uri = WSURI + deviceId;

        //获取websocketClient对象
        WebSocketConfig webSocket = new WebSocketConfig();
        WebSocketClient wsConn = webSocket.getConn(uri, json.toString());

        ThreadUtil threadUtil = new ThreadUtil();

        Map<String, String> result = threadUtil.ThreadSleep();


        if (result.get("status").equals("device_no_connect")) {
            JSONObject be_result = new JSONObject(result);
            WebSocketConfig.jsonMaps.clear();
            return be_result.toString();
        } else {
            JSONObject data = new JSONObject(result);
            WebSocketConfig.jsonMaps.clear();

            wsConn.close();

            json.set("getSetUseTime", json.get("setUseDay") + "天" + json.get("setUseHour") + "时" + json.get("setUseMin") + "分" + json.get("setUseSec") + "秒");

            TableStringUtil util = new TableStringUtil();
            boolean batteryTime = util.table_battery_time(json.toString());

            if (batteryTime)
                return data.toString();
            else{
                JSONObject res = JSONUtil.createObj()
                        .set("status", "wait");
                return res.toString();
            }
        }
    }


}
