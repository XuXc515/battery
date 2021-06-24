package com.xxc.service;

import com.xxc.domain.Battery;
import com.xxc.domain.BatteryStatus;
import com.xxc.domain.BatteryType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxc
 * @date 2020/8/6 - 8:56
 */
public interface ManageService {

    //分页查询电池
//    List<Battery> queryPageBattery(@Param("curr")int curr, @Param("limit")int limit);
//    int queryPageCountBattery();

    //保存电池
    void saveBattery(Battery battery);

    //根据id查询电池信息
    Battery findBatteryById(Integer id);


    //更新电池信息
    void updateBattery(Battery battery);

    //根据电池编号分页查询电池
//    List<Battery> queryPageFindBattery(@Param("curr")int curr, @Param("limit")int limit, String batteryId);
//    int queryPageCountFindBattery(String batteryId);

    //删除电池
    void deleteBattery(Integer id);

    //批量删除电池
    void deleteAllBattery(String[] ids);


    //分页查询电池类型
//    List<BatteryType> queryPageBatteryType(@Param("curr")int curr, @Param("limit")int limit);
//    int queryPageCountBatteryType();
//
//    //保存电池类型
//    void saveBatteryType(BatteryType batteryType);
//
//    //根据id查询电池类型
//    BatteryType findBatteryTypeById(Integer id);
//    //更新电池信息
//    void updateBatteryType(BatteryType batteryType);

    //保存电池金额
    void saveBatteryPrice(Battery battery);

    //根据类型编号分页查询电池类型
//    List<BatteryType> queryPageFindBatteryType(@Param("curr")int curr, @Param("limit")int limit, String typeId);
//    int queryPageCountFindBatteryType(String typeId);
//
//    //根据状态编号分页查询状态
//    List<BatteryStatus> queryPageFindBatteryStatus(@Param("curr")int curr, @Param("limit")int limit, String statusId);
//    int queryPageCountFindBatteryStatus(String statusId);
//
//    //删除电池类型
//    void deleteBatteryType(Integer id);
}
