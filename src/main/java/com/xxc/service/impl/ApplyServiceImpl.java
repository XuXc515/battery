package com.xxc.service.impl;

import com.xxc.dao.SaleMapper;
import com.xxc.domain.Sale;
import com.xxc.service.ApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xxc
 * @date 2020/7/30 - 17:09
 */
@Service("applyService")
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private SaleMapper saleMapper;

    @Override
    public List<Sale> queryPageApply(int curr, int limit) {
        curr = (curr - 1) * limit;
        return saleMapper.queryPageApply(curr, limit);
    }

    @Override
    public int queryPageCountApply() {
        return saleMapper.queryPageCountApply();
    }

    @Override
    public List<Sale> findByDateApply(int curr, int limit, Date start, Date end, String batteryId, String userAccount) {
        curr = (curr - 1) * limit;
        return saleMapper.findByDateApply(curr, limit, start, end, batteryId, userAccount);
    }

    @Override
    public int findByDateApplyCount(Date start, Date end, String batteryId, String userAccount) {
        return saleMapper.findByDateApplyCount(start, end, batteryId, userAccount);
    }

    @Override
    public Sale findUserByIdApply(Integer id) {
        return saleMapper.findUserByIdApply(id);
    }

    @Override
    public void updateApply(Integer id, String batteryId) {
        saleMapper.updateApply(id, batteryId);
    }

    @Override
    public void deleteApply(Integer id) {
        saleMapper.deleteApply(id);
    }

    @Override
    public Sale findSaleByDeviceId(String batteryId) {
        return saleMapper.findSaleByDeviceId(batteryId);
    }

    @Override
    public Sale findSaleByBatteryId(String batteryId) {
        return saleMapper.findSaleByBatteryId(batteryId);
    }
}
