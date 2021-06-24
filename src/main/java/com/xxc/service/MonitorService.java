package com.xxc.service;

import com.xxc.domain.Battery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxc
 * @date 2020/8/6 - 16:38
 */
public interface MonitorService {

    //分页查询电池
    List<Battery> queryPageBattery(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBattery();

    //根据电池编号分页查询电池
    List<Battery> queryPageFindBattery(@Param("curr") int curr, @Param("limit") int limit, String batteryId);

    int queryPageCountFindBattery(String batteryId);
}
