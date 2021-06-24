package com.xxc.dao;

import com.xxc.domain.Sale;
import com.xxc.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SaleMapper {

    /*
    ApplyService
     */

    //分页查询用户租赁信息
    List<Sale> queryPageApply(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountApply();

    //根据日期查询信息
    List<Sale> findByDateApply(int curr, int limit, Date start, Date end, String batteryId, String userAccount);

    int findByDateApplyCount(Date start, Date end, String batteryId, String userAccount);

    //根据用户名查询信息
    Sale findUserByIdApply(@Param("id") Integer id);

    //更新租赁信息
    void updateApply(Integer id, String batteryId);

    //删除订单
    void deleteApply(Integer id);

    //根据电池编号查询订单信息
    Sale findSaleByDeviceId(String batteryId);

    //根据电池编号查询订单信息
    Sale findSaleByBatteryId(String batteryId);

    /*
    CostService
     */
    //分页查询用户账户信息
    List<Sale> queryPageCost(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountCost();

    //根据订单号和名字查询信息
    List<Sale> findBySaleIdAndNameCost(@Param("curr") int curr, @Param("limit") int limit, String saleId, String userName);

    int findBySaleIdAndNameCostCount(String saleId, String userName);

    //根据用户account查询电池id
    List<Sale> findBatteryIdByAccount(String userAccount);

    /*
    AliPayService
     */
    //查询最后一行数据订单编号
    Sale findLastSaleId();

    //保存订单
    void saveBatteryOrder(Sale sale);

    //支付成功，更改订单状态
    void changeBatteryOrder(String saleId);

    //根据订单编号获取订单信息
    Sale saleByOrderId(String saleId);

    //支付失败，删除订单
    void deleteBatteryOrder(String saleId);

    /*
    UserService
     */
    //根据用户account查询租赁订单
    List<Sale> findSaleByAccount(String userAccount);

    //根据订单编号查询租赁订单
    Sale findSaleBySaleId(String saleId);

    //电池累积租出数
    Integer batteryRentalCount();

    //当前电池租出数
    Integer batteryCurrentRentalCount();
}