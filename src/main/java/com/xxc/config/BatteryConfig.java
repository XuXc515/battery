package com.xxc.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author xxc
 * @date 2021/1/12 - 10:15
 */
@Configuration
public class BatteryConfig {

    //电池有关的字符
    public static final String[] BATTERYSTRING = {"devStatus", "getDevId1", "getDevId2", "setTimeStamp1", "setTimeStamp2",
            "setHeartBeatPeriod", "devRegisterServer", "devLoginServer", "devConnectStatus", "setDevPollPeriod",
            "getDevPollPeriod", "getDevStatus", "getDevGpsStatus", "getBdgsa", "getGpgsa", "getGnrmc", "getGnvtg",
            "getGngga", "getGpgsv", "getBdgsv", "devElect", "getDevSoc", "getDevGps", "getDevTemp", "getDevLonLat",
            "getUseDay", "getUseHour", "getUseMin", "getUseSec", "setUseDay", "setUseHour", "setUseMin", "setUseSec",
            "getSetUseDay", "getSetUseHour", "getSetUseMin", "getSetUseSec", "getSetUseTime", "getUseTime", "getDevElect"};

    //电池表有关的字符
    public static final String[] TABLE_BATTERY_STRING = {"getDevGps"};

    //电池设备表有关的字符
    public static final String[] TABLE_BATTERY_TYPE_STRING = {"getDevSoc"};

    //电池状态表有关的字符
    public static final String[] TABLE_BATTERY_STATUS_STRING = {"getDevTemp", "getDevElect"};

    //电池时间表有关的字符
    public static final String[] TABLE_BATTERY_TIME_STRING = {
            "getUseDay", "getUseHour", "getUseMin", "getUseSec", "setUseDay", "setUseHour", "setUseMin", "setUseSec",
            "getSetUseDay", "getSetUseHour", "getSetUseMin", "getSetUseSec", "getSetUseTime", "getUseTime"};
}
