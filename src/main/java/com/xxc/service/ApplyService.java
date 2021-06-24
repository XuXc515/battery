package com.xxc.service;

import com.xxc.domain.Sale;

import java.util.Date;
import java.util.List;

/**
 * @author xxc
 * @date 2020/7/30 - 17:09
 */
public interface ApplyService {

    /*
    ApplyController
     */

    //分页查询用户租赁信息
    List<Sale> queryPageApply(int curr, int limit);

    int queryPageCountApply();

    //根据日期查询信息
    List<Sale> findByDateApply(int curr, int limit, Date start, Date end, String batteryId, String userAccount);

    int findByDateApplyCount(Date start, Date end, String batteryId, String userAccount);

    //根据用户名查询信息
    Sale findUserByIdApply(Integer id);

    //更新租赁信息
    void updateApply(Integer id, String batteryId);

    //删除订单
    void deleteApply(Integer id);

    //根据电池编号查询订单信息
    Sale findSaleByDeviceId(String batteryId);

    //根据电池编号查询订单信息
    Sale findSaleByBatteryId(String batteryId);
}
