package com.xxc.service.impl;

import com.xxc.dao.BatteryMapper;
import com.xxc.domain.Battery;
import com.xxc.service.MonitorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xxc
 * @date 2020/8/6 - 16:37
 */
@Service("monitorService")
public class MonitorServiceImpl implements MonitorService {

    @Resource
    private BatteryMapper batteryMapper;

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
}
