package com.xxc.util;

import com.alibaba.fastjson.JSON;
import com.xxc.config.BatteryConfig;
import com.xxc.domain.Battery;
import com.xxc.domain.BatteryStatus;
import com.xxc.domain.BatteryTime;
import com.xxc.domain.BatteryType;
import com.xxc.service.BatteryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author xxc
 * @date 2021/3/16 9:22
 */
@Component
public class TableStringUtil {

    @Resource
    private BatteryService batteryService;

    public static BatteryService utilService;    // 添加一个本类类型的静态字段

    /**
     * 创建一个初始化方法，贴上@PostConstruct 标签，用于注入bean
     */
    @PostConstruct
    public void init() {
        utilService = batteryService;
    }

    public boolean table_battery(String data){

        String[] table_battery_String = BatteryConfig.TABLE_BATTERY_STRING;

        HashMap map = JSON.parseObject(data, HashMap.class);

        String deviceId = null;
        //遍历前端json数据的key，找到deviceId
        for (Object key : map.keySet()) {
            if (key == "deviceId") {
                deviceId = map.get(key).toString();
            }
        }

        Battery battery = utilService.findBatteryByDeviceId(deviceId);
        boolean b;
        if (battery == null){
            battery.setDeviceId(deviceId);
            battery.setTypeId(deviceId);
            battery.setStatusId(deviceId);
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_String.length; i++) {
                    if (key == table_battery_String[0])
                        battery.setAddress(map.get(key).toString());
                }
            }
            b = utilService.saveDevice(battery);
        }
        else{
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_String.length; i++) {
                    if (key == table_battery_String[0])
                        battery.setAddress(map.get(key).toString());
                }
            }
            b = utilService.updateDevice(battery);
        }
        return b;
    }

    public boolean table_battery_type(String data){

        String[] table_battery_type_String = BatteryConfig.TABLE_BATTERY_TYPE_STRING;

        HashMap map = JSON.parseObject(data, HashMap.class);

        String typeId = null;
        //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
        for (Object key : map.keySet()) {
            if (key == "deviceId") {
                typeId = map.get(key).toString();
            }
        }
        BatteryType batteryType = utilService.findBatteryTypeByTypeId(typeId);
        boolean b;
        if (batteryType == null){
            batteryType.setTypeId(typeId);
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_type_String.length; i++) {
                    if (key == table_battery_type_String[0])
                        batteryType.setBatterySoc(map.get(key).toString());
                }
            }
            b = utilService.saveDeviceType(batteryType);
        }

        else{
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_type_String.length; i++) {
                    if (key == table_battery_type_String[0])
                        batteryType.setBatterySoc(map.get(key).toString());
                }
            }
            b = utilService.updateDeviceType(batteryType);
        }

        return b;
    }

    public boolean table_battery_status(String data){

        String[] table_battery_status_String = BatteryConfig.TABLE_BATTERY_STATUS_STRING;

        HashMap map = JSON.parseObject(data, HashMap.class);

        String statusId = null;

        //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
        for (Object key : map.keySet()) {
            if (key == "deviceId") {
                statusId = map.get(key).toString();
            }
        }

        BatteryStatus batteryStatus = utilService.findBatteryStatusByStatusId(statusId);
        boolean b;
        if (batteryStatus == null){
            batteryStatus.setStatusId(statusId);
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_status_String.length; i++) {
                    if (key == table_battery_status_String[0])
                        batteryStatus.setBatteryTemperature(map.get(key).toString());
                    if (key == table_battery_status_String[1])
                        batteryStatus.setBatteryStatus(map.get(key).toString());
                }
            }
            b = utilService.saveDeviceStatus(batteryStatus);
        }
        else{
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_status_String.length; i++) {
                    if (key == table_battery_status_String[0])
                        batteryStatus.setBatteryTemperature(map.get(key).toString());
                    if (key == table_battery_status_String[1])
                        batteryStatus.setBatteryStatus(map.get(key).toString());
                }
            }
            b = utilService.updateDeviceStatus(batteryStatus);
        }
        return b;
    }

    public boolean table_battery_time(String data){

        String[] table_battery_time_String = BatteryConfig.TABLE_BATTERY_TIME_STRING;

        HashMap map = JSON.parseObject(data, HashMap.class);

        String deviceId = null;

        //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
        for (Object key : map.keySet()) {
            if (key == "deviceId") {
                deviceId = map.get(key).toString();
            }
        }

        BatteryTime batteryTime = utilService.findBatteryTimeByDeviceId(deviceId);
        boolean b;
        if (batteryTime == null){
            batteryTime.setDeviceId(deviceId);
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_time_String.length; i++) {
                    if (key == table_battery_time_String[0])
                        batteryTime.setUsedDay(map.get(key).toString());
                    if (key == table_battery_time_String[1])
                        batteryTime.setUsedHour(map.get(key).toString());
                    if (key == table_battery_time_String[2])
                        batteryTime.setUsedMin(map.get(key).toString());
                    if (key == table_battery_time_String[3])
                        batteryTime.setUsedSec(map.get(key).toString());
                    if (key == table_battery_time_String[4] || key == table_battery_time_String[8])
                        batteryTime.setUseDay(map.get(key).toString());
                    if (key == table_battery_time_String[5] || key == table_battery_time_String[9])
                        batteryTime.setUseHour(map.get(key).toString());
                    if (key == table_battery_time_String[6] || key == table_battery_time_String[10])
                        batteryTime.setUseMin(map.get(key).toString());
                    if (key == table_battery_time_String[7] || key == table_battery_time_String[11])
                        batteryTime.setUseSec(map.get(key).toString());
                    if (key == table_battery_time_String[12])
                        batteryTime.setUseTotalTime(map.get(key).toString());
                    if (key == table_battery_time_String[13])
                        batteryTime.setUsedTotalTime(map.get(key).toString());
                }
            }
            b = utilService.saveDeviceTime(batteryTime);
        }
        else{
            //遍历前端json数据的key，与包含所有字符集的数组遍历对比，动态获取接收到的字符
            for (Object key : map.keySet()) {
                for (int i = 0; i < table_battery_time_String.length; i++) {
                    if (key == table_battery_time_String[0])
                        batteryTime.setUsedDay(map.get(key).toString());
                    if (key == table_battery_time_String[1])
                        batteryTime.setUsedHour(map.get(key).toString());
                    if (key == table_battery_time_String[2])
                        batteryTime.setUsedMin(map.get(key).toString());
                    if (key == table_battery_time_String[3])
                        batteryTime.setUsedSec(map.get(key).toString());
                    if (key == table_battery_time_String[4] || key == table_battery_time_String[8])
                        batteryTime.setUseDay(map.get(key).toString());
                    if (key == table_battery_time_String[5] || key == table_battery_time_String[9])
                        batteryTime.setUseHour(map.get(key).toString());
                    if (key == table_battery_time_String[6] || key == table_battery_time_String[10])
                        batteryTime.setUseMin(map.get(key).toString());
                    if (key == table_battery_time_String[7] || key == table_battery_time_String[11])
                        batteryTime.setUseSec(map.get(key).toString());
                    if (key == table_battery_time_String[12])
                        batteryTime.setUsedTotalTime(map.get(key).toString());
                    if (key == table_battery_time_String[13])
                        batteryTime.setUseTotalTime(map.get(key).toString());
                }
            }
            b = utilService.updateDeviceTime(batteryTime);
        }
        return b;
    }

}
