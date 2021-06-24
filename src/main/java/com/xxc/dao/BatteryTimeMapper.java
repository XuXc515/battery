package com.xxc.dao;

import com.xxc.domain.BatteryTime;


public interface BatteryTimeMapper {

    //根据电池编号查询电池类型
    BatteryTime findBatteryTimeByDeviceId(String deviceId);
    //插入电池类型
    boolean saveDeviceTime(BatteryTime batteryTime);
    //更新电池类型
    boolean updateDeviceTime(BatteryTime batteryTime);
}