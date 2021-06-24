package com.xxc.service;

import com.xxc.domain.Battery;
import com.xxc.domain.BatteryStatus;
import com.xxc.domain.BatteryTime;
import com.xxc.domain.BatteryType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xxc
 * @date 2020/8/8 - 14:16
 */
public interface BatteryService {

    //批量更新插入数据库
    Boolean insertBatteryList(List<Battery> list);

    //分页查询电池
    List<Battery> queryPageBattery(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBattery();

    //根据电池编号分页查询电池
    List<Battery> queryPageFindBattery(@Param("curr") int curr, @Param("limit") int limit, String batteryId);

    int queryPageCountFindBattery(String batteryId);


    //批量更新插入数据库
    Boolean insertBatteryTypeList(List<BatteryType> list);

    //分页查询电池类型
    List<BatteryType> queryPageBatteryType(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBatteryType();

    //根据类型编号分页查询电池类型
    List<BatteryType> queryPageFindBatteryType(@Param("curr") int curr, @Param("limit") int limit, String typeId);

    int queryPageCountFindBatteryType(String typeId);

    //查询全部电池类型
    List<BatteryType> findAllBatteryType();


    //电池状态批量更新插入数据库
    Boolean insertBatteryStatusList(List<BatteryStatus> list);

    //分页查询电池状态
    List<BatteryStatus> queryPageBatteryStatus(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBatteryStatus();

    //根据状态编号分页查询状态
    List<BatteryStatus> queryPageFindBatteryStatus(@Param("curr") int curr, @Param("limit") int limit, String statusId);

    int queryPageCountFindBatteryStatus(String statusId);

    //查询全部电池状态
    List<BatteryStatus> findAllBatteryStatus();


    //分页查询未填写电池金额
    List<Battery> queryPageBatteryPrice(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBatteryPrice();


    //分页查询维修中电池
    List<Battery> queryPageBatteryRepair(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBatteryRepair();

    //根据电池id分页查询维修中电池
    List<Battery> queryFindPageBatteryRepair(@Param("curr") int curr, @Param("limit") int limit, String deviceId);

    int queryFindPageCountBatteryRepair(String deviceId);

    //添加电池维修
    void addBatteryRepair(String deviceId, String faultDetail);

    //删除电池的在维修状态
    void deleteBatteryRepair(Integer id);

    //批量删除电池的在维修状态
    void deleteAllBatteryRepair(String[] ids);


    //根据电池id查询电池
    Battery findBatteryByDeviceId(String deviceId);


    //根据id查询电池状态
    Battery findBatteryInfoById(String deviceId);


    //查询 所有电池 总数量
    Integer batteryCount();

    //查询 在线电池 总数量
    Integer batteryOnLineCount();

    //查询 离线电池 总数量
    Integer batteryNoLineCount();

    //查询 休眠电池 总数量
    Integer batterySleepCount();

    //查询 报错电池 总数量
    Integer batteryErrorCount();




    //分页查询电池信息
    List<Battery> queryPageBatteryInfo(@Param("curr") int curr, @Param("limit") int limit);
    int queryPageCountBatteryInfo();


    //保存电池
    boolean saveDevice(Battery battery);
    //更新电池信息
    boolean updateDevice(Battery battery);


    //根据电池编号查询电池类型
    BatteryType findBatteryTypeByTypeId(String typeId);
    //插入电池类型
    boolean saveDeviceType(BatteryType batteryType);
    //更新电池类型
    boolean updateDeviceType(BatteryType batteryType);


    //根据状态编号查询状态
    BatteryStatus findBatteryStatusByStatusId(String statusId);
    //插入电池状态
    boolean saveDeviceStatus(BatteryStatus batteryStatus);
    //更新电池状态
    boolean updateDeviceStatus(BatteryStatus batteryStatus);


    //根据电池编号查询电池类型
    BatteryTime findBatteryTimeByDeviceId(String deviceId);
    //插入电池类型
    boolean saveDeviceTime(BatteryTime batteryTime);
    //更新电池类型
    boolean updateDeviceTime(BatteryTime batteryTime);

}
