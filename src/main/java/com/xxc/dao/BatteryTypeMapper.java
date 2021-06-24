package com.xxc.dao;

import com.xxc.domain.BatteryType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BatteryTypeMapper {

    //批量更新插入数据库
    Boolean insertBatteryTypeList(List<BatteryType> list);


    //分页查询电池类型
    List<BatteryType> queryPageBatteryType(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountBatteryType();

    //保存电池类型
    void saveBatteryType(BatteryType batteryType);

    //根据id查询电池类型
    BatteryType findBatteryTypeById(Integer id);

    //更新电池类型
    void updateBatteryType(BatteryType batteryType);

    //删除电池类型
    void deleteBatteryType(Integer id);


    //根据电池编号查询电池类型
    BatteryType findBatteryTypeByTypeId(String typeId);
    //插入电池类型
    boolean saveDeviceType(BatteryType batteryType);
    //更新电池类型
    boolean updateDeviceType(BatteryType batteryType);

}