package com.xxc.dao;

import com.xxc.domain.Battery;
import com.xxc.domain.BatteryStatus;
import com.xxc.domain.BatteryTime;
import com.xxc.domain.BatteryType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BatteryMapper {

    //批量更新插入数据库
    Boolean insertBatteryList(List<Battery> list);


    //分页查询电池
    List<Battery> queryPageBattery(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBattery();

    //根据电池编号分页查询电池
    List<Battery> queryPageFindBattery(@Param("curr") int curr, @Param("limit") int limit, String deviceId);

    int queryPageCountFindBattery(String deviceId);


    //分页查询电池类型
    List<BatteryType> queryPageBatteryType(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBatteryType();

    //根据类型编号分页查询电池类型
    List<BatteryType> queryPageFindBatteryType(@Param("curr") int curr, @Param("limit") int limit, String typeId);

    int queryPageCountFindBatteryType(String typeId);

    //查询全部电池类型
    List<BatteryType> findAllBatteryType();


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

    //保存电池金额
    void saveBatteryPrice(Battery battery);


    //保存电池
    void saveBattery(Battery battery);

    //根据id查询电池信息
    Battery findBatteryById(Integer id);

    //更新电池信息
    void updateBattery(Battery battery);

    //删除电池
    void deleteBattery(Integer id);

    //批量删除电池
    void deleteAllBattery(String[] ids);

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


    //根据id查询电池
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
}