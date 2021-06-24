package com.xxc.service.impl;

import com.xxc.dao.BatteryMapper;
import com.xxc.dao.BatteryTypeMapper;
import com.xxc.domain.Battery;
import com.xxc.service.ManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xxc
 * @date 2020/8/6 - 8:56
 */
@Service("manageService")
public class ManageServiceImpl implements ManageService {

    @Resource
    private BatteryMapper batteryMapper;

    @Resource
    private BatteryTypeMapper batteryTypeMapper;

//    @Override
//    public List<Battery> queryPageBattery(int curr, int limit) {
//        curr=(curr-1)*limit;
//        return batteryMapper.queryPageBattery(curr,limit);
//    }
//
//    @Override
//    public int queryPageCountBattery() {
//        return batteryMapper.queryPageCountBattery();
//    }

    @Override
    public void saveBattery(Battery battery) {
        batteryMapper.saveBattery(battery);
    }

    @Override
    public Battery findBatteryById(Integer id) {
        return batteryMapper.findBatteryById(id);
    }

    @Override
    public void updateBattery(Battery battery) {
        batteryMapper.updateBattery(battery);
    }

//    @Override
//    public List<Battery> queryPageFindBattery(int curr, int limit, String batteryId) {
//        curr=(curr-1)*limit;
//        return batteryMapper.queryPageFindBattery(curr,limit,batteryId);
//    }
//
//    @Override
//    public int queryPageCountFindBattery(String batteryId) {
//        return batteryMapper.queryPageCountFindBattery(batteryId);
//    }

    @Override
    public void deleteBattery(Integer id) {
        batteryMapper.deleteBattery(id);
    }

    @Override
    public void deleteAllBattery(String[] ids) {
        batteryMapper.deleteAllBattery(ids);
    }

//    @Override
//    public List<BatteryType> queryPageBatteryType(int curr, int limit) {
//        curr=(curr-1)*limit;
//        return batteryTypeMapper.queryPageBatteryType(curr,limit);
//    }
//
//    @Override
//    public int queryPageCountBatteryType() {
//        return batteryTypeMapper.queryPageCountBatteryType();
//    }
//
//    @Override
//    public void saveBatteryType(BatteryType batteryType) {
//        batteryTypeMapper.saveBatteryType(batteryType);
//    }
//
//    @Override
//    public BatteryType findBatteryTypeById(Integer id) {
//        return batteryTypeMapper.findBatteryTypeById(id);
//    }
//
//    @Override
//    public void updateBatteryType(BatteryType batteryType) {
//        batteryTypeMapper.updateBatteryType(batteryType);
//    }

    @Override
    public void saveBatteryPrice(Battery battery) {
        batteryMapper.saveBatteryPrice(battery);
    }

//    @Override
//    public List<BatteryType> queryPageFindBatteryType(int curr, int limit, String typeId) {
//        curr=(curr-1)*limit;
//        return batteryMapper.queryPageFindBatteryType(curr,limit,typeId);
//    }
//
//    @Override
//    public int queryPageCountFindBatteryType(String typeId) {
//        return batteryMapper.queryPageCountFindBatteryType(typeId);
//    }
//
//    @Override
//    public List<BatteryStatus> queryPageFindBatteryStatus(int curr, int limit, String statusId) {
//        curr=(curr-1)*limit;
//        return batteryMapper.queryPageFindBatteryStatus(curr,limit,statusId);
//    }
//
//    @Override
//    public int queryPageCountFindBatteryStatus(String statusId) {
//        return batteryMapper.queryPageCountFindBatteryStatus(statusId);
//    }
//
//    @Override
//    public void deleteBatteryType(Integer id) {
//        batteryTypeMapper.deleteBatteryType(id);
//    }
}
