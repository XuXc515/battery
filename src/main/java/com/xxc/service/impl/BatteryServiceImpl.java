package com.xxc.service.impl;

import com.xxc.dao.BatteryMapper;
import com.xxc.dao.BatteryStatusMapper;
import com.xxc.dao.BatteryTimeMapper;
import com.xxc.dao.BatteryTypeMapper;
import com.xxc.domain.Battery;
import com.xxc.domain.BatteryStatus;
import com.xxc.domain.BatteryTime;
import com.xxc.domain.BatteryType;
import com.xxc.service.BatteryService;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xxc
 * @date 2020/8/8 - 14:16
 */
@Service("batteryService")
public class BatteryServiceImpl implements BatteryService {

    @Resource
    private BatteryMapper batteryMapper;

    @Resource
    private BatteryTypeMapper batteryTypeMapper;

    @Resource
    private BatteryStatusMapper batteryStatusMapper;

    @Resource
    private BatteryTimeMapper batteryTimeMapper;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Override
    public Boolean insertBatteryList(List<Battery> list) {
        return batteryMapper.insertBatteryList(list);
    }

    @Override
    public List<Battery> queryPageBattery(int curr, int limit) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageBattery(curr, limit);
    }

    @Override
    public int queryPageCountBattery() {
        return batteryMapper.queryPageCountBattery();
    }

    @Override
    public List<Battery> queryPageFindBattery(int curr, int limit, String batteryId) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageFindBattery(curr, limit, batteryId);
    }

    @Override
    public int queryPageCountFindBattery(String batteryId) {
        return batteryMapper.queryPageCountFindBattery(batteryId);
    }

    @Override
    public Boolean insertBatteryTypeList(List<BatteryType> list) {
        return batteryTypeMapper.insertBatteryTypeList(list);
    }

    @Override
    public List<BatteryType> findAllBatteryType() {
        return batteryMapper.findAllBatteryType();
    }

    @Override
    public List<BatteryType> queryPageBatteryType(int curr, int limit) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageBatteryType(curr, limit);
    }

    @Override
    public int queryPageCountBatteryType() {
        return batteryMapper.queryPageCountBatteryType();
    }

    @Override
    public List<BatteryType> queryPageFindBatteryType(int curr, int limit, String typeId) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageFindBatteryType(curr, limit, typeId);
    }

    @Override
    public int queryPageCountFindBatteryType(String typeId) {
        return batteryMapper.queryPageCountFindBatteryType(typeId);
    }


    @Override
    public Boolean insertBatteryStatusList(List<BatteryStatus> list) {
        return batteryStatusMapper.insertBatteryStatusList(list);
    }

    @Override
    public List<BatteryStatus> findAllBatteryStatus() {
        return batteryMapper.findAllBatteryStatus();
    }

    @Override
    public List<BatteryStatus> queryPageBatteryStatus(int curr, int limit) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageBatteryStatus(curr, limit);
    }

    @Override
    public int queryPageCountBatteryStatus() {
        return batteryMapper.queryPageCountBatteryStatus();
    }

    @Override
    public List<Battery> queryPageBatteryPrice(int curr, int limit) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageBatteryPrice(curr, limit);
    }

    @Override
    public int queryPageCountBatteryPrice() {
        return batteryMapper.queryPageCountBatteryPrice();
    }


    @Override
    public List<BatteryStatus> queryPageFindBatteryStatus(int curr, int limit, String statusId) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageFindBatteryStatus(curr, limit, statusId);
    }

    @Override
    public int queryPageCountFindBatteryStatus(String statusId) {
        return batteryMapper.queryPageCountFindBatteryStatus(statusId);
    }

    @Override
    public List<Battery> queryPageBatteryRepair(int curr, int limit) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageBatteryRepair(curr, limit);
    }

    @Override
    public int queryPageCountBatteryRepair() {
        return batteryMapper.queryPageCountBatteryRepair();
    }

    @Override
    public List<Battery> queryFindPageBatteryRepair(int curr, int limit, String deviceId) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryFindPageBatteryRepair(curr, limit, deviceId);
    }

    @Override
    public int queryFindPageCountBatteryRepair(String deviceId) {
        return batteryMapper.queryFindPageCountBatteryRepair(deviceId);
    }

    @Override
    public Battery findBatteryByDeviceId(String deviceId) {
        return batteryMapper.findBatteryByDeviceId(deviceId);
    }

    @Override
    public void addBatteryRepair(String deviceId, String faultDetail) {
        batteryMapper.addBatteryRepair(deviceId, faultDetail);
    }

    @Override
    public void deleteBatteryRepair(Integer id) {
        batteryMapper.deleteBatteryRepair(id);
    }

    @Override
    public void deleteAllBatteryRepair(String[] ids) {
        batteryMapper.deleteAllBatteryRepair(ids);
    }

    @Override
    public Battery findBatteryInfoById(String deviceId) {
//        return batteryMapper.findBatteryInfoById(deviceId);
        Battery battery = (Battery) valueOperations.get(deviceId);
        if (battery == null) {
            Battery batteryInfoById = batteryMapper.findBatteryInfoById(deviceId);
            valueOperations.set(deviceId, batteryInfoById);
            return batteryInfoById;
        } else
            return battery;
    }

    @Override
    public Integer batteryCount() {
        return batteryMapper.batteryCount();
    }

    @Override
    public Integer batteryOnLineCount() {
        return batteryMapper.batteryOnLineCount();
    }

    @Override
    public Integer batteryNoLineCount() {
        return batteryMapper.batteryNoLineCount();
    }

    @Override
    public Integer batterySleepCount() {
        return batteryMapper.batterySleepCount();
    }

    @Override
    public Integer batteryErrorCount() {
        return batteryMapper.batteryErrorCount();
    }

    @Override
    public List<Battery> queryPageBatteryInfo(int curr, int limit) {
        curr = (curr - 1) * limit;
        return batteryMapper.queryPageBatteryInfo(curr, limit);
    }

    @Override
    public int queryPageCountBatteryInfo() {
        return batteryMapper.queryPageCountBatteryInfo();
    }

    @Override
    public boolean saveDevice(Battery battery) {
        return batteryMapper.saveDevice(battery);
    }

    @Override
    public boolean updateDevice(Battery battery) {
        return batteryMapper.updateDevice(battery);
    }

    @Override
    public BatteryType findBatteryTypeByTypeId(String typeId) {
        return batteryTypeMapper.findBatteryTypeByTypeId(typeId);
    }

    @Override
    public boolean saveDeviceType(BatteryType batteryType) {
        return batteryTypeMapper.saveDeviceType(batteryType);
    }

    @Override
    public boolean updateDeviceType(BatteryType batteryType) {
        return batteryTypeMapper.updateDeviceType(batteryType);
    }

    @Override
    public BatteryStatus findBatteryStatusByStatusId(String statusId) {
        return batteryStatusMapper.findBatteryStatusByStatusId(statusId);
    }

    @Override
    public boolean saveDeviceStatus(BatteryStatus batteryStatus) {
        return batteryStatusMapper.saveDeviceStatus(batteryStatus);
    }

    @Override
    public boolean updateDeviceStatus(BatteryStatus batteryStatus) {
        return batteryStatusMapper.updateDeviceStatus(batteryStatus);
    }

    @Override
    public BatteryTime findBatteryTimeByDeviceId(String deviceId) {
        return batteryTimeMapper.findBatteryTimeByDeviceId(deviceId);
    }

    @Override
    public boolean saveDeviceTime(BatteryTime batteryTime) {
        return batteryTimeMapper.saveDeviceTime(batteryTime);
    }

    @Override
    public boolean updateDeviceTime(BatteryTime batteryTime) {
        return batteryTimeMapper.updateDeviceTime(batteryTime);
    }

}
