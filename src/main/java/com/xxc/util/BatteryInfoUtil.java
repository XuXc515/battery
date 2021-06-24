package com.xxc.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.xxc.domain.Battery;
import com.xxc.domain.BatteryStatus;
import com.xxc.domain.BatteryType;
import com.xxc.service.BatteryService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xxc
 * @date 2020/8/11 - 14:47
 */
@Component
@EnableScheduling
public class BatteryInfoUtil {

    @Resource
    private BatteryService batteryService;

    private static String URL = "http://8.129.79.195:8099";

//    @Scheduled(cron = "0/5 * * * * ?")
    public void getBattery() {

        String url = URL + "/api/iotdevice/selectall";//指定URL
        //发送get请求并接收响应数据
        String data = HttpRequest.get(url).execute().body();

        String battery = JSONUtil.parseObj(data).get("data").toString();

//        System.out.println(battery);
//        String battery = jsonObject.get("data").toString();
//        String battery = o.toString();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Type type = new TypeToken<ArrayList<Battery>>() {
        }.getType();
        List<Battery> list = gson.fromJson(battery, type);

//        List<Battery> jsonToPojo  = new ArrayList<Battery>();
//        List<Battery> jsonToList  = JsonUtils.jsonToList(jsonToPojo, Battery.class);

//        System.out.println(list);

        Boolean b = batteryService.insertBatteryList(list);

    }

//    @Scheduled(cron = "0/5 * * * * ?")
    public void getBatteryStatus() {

        String url = URL + "/api/devicestatus/selectall";//指定URL
        //发送get请求并接收响应数据
        String data = HttpRequest.get(url).execute().body();

//        JSONObject jsonObject = JSONUtil.parseObj(data);
//        Object o = jsonObject.get("data");
//        String s = o.toString();

        String batteryStatus = JSONUtil.parseObj(data).get("data").toString();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Type type = new TypeToken<ArrayList<BatteryStatus>>() {
        }.getType();
        List<BatteryStatus> list = gson.fromJson(batteryStatus, type);

        Boolean b = batteryService.insertBatteryStatusList(list);

    }

//    @Scheduled(cron = "0/5 * * * * ?")
    public void getBatteryType() {

        String url = URL + "/api/iotdevicetype/selectall";//指定URL
        //发送get请求并接收响应数据
        String data = HttpRequest.get(url).execute().body();

//        JSONObject jsonObject = JSONUtil.parseObj(data);
//        Object o = jsonObject.get("data");
//        String s = o.toString();

        String batteryType = JSONUtil.parseObj(data).get("data").toString();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Type type = new TypeToken<ArrayList<BatteryType>>() {
        }.getType();
        List<BatteryType> list = gson.fromJson(batteryType, type);

        Boolean b = batteryService.insertBatteryTypeList(list);

    }


}
