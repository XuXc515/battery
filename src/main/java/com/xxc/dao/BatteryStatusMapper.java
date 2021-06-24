package com.xxc.dao;

import com.xxc.domain.BatteryStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BatteryStatusMapper {

    //批量更新插入数据库
    Boolean insertBatteryStatusList(List<BatteryStatus> list);

    //分页查询电池状态
    List<BatteryStatus> queryPageBatteryStatus(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBatteryStatus();


    //根据状态编号查询状态
    BatteryStatus findBatteryStatusByStatusId(String statusId);
    //插入电池状态
    boolean saveDeviceStatus(BatteryStatus batteryStatus);
    //更新电池状态
    boolean updateDeviceStatus(BatteryStatus batteryStatus);
}