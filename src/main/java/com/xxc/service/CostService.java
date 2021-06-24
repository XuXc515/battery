package com.xxc.service;

import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import com.xxc.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xxc
 * @date 2020/7/31 - 15:29
 */
public interface CostService {

    //分页查询用户账户信息
    List<Sale> queryPageCost(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountCost();

    //根据订单号和名字查询信息
    List<Sale> findBySaleIdAndNameCost(@Param("curr") int curr, @Param("limit") int limit, String saleId, String userName);

    int findBySaleIdAndNameCostCount(String saleId, String userName);

    //根据用户account查询电池id
    List<Sale> findBatteryIdByAccount(String userAccount);

    //分页查询用户充值记录
    List<Recharge> queryPageRecharge(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountRecharge();

    //根据日期查询用户充值信息
    List<Recharge> findByDateRecharge(@Param("curr") int curr, @Param("limit") int limit, Date start, Date end, String userAccount);

    int findByDateRechargeCount(Date start, Date end, String userAccount);

    //分页查询欠款用户
    List<User> queryPageArrearsUser(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountArrearsUser();

    //根据用户编号和用户账户查询信息
    List<User> findArrearsUserByUserIdAndAccount(@Param("curr") int curr, @Param("limit") int limit, String userId, String userAccount);

    int findArrearsUserByUserIdAndAccountCount(String userId, String userAccount);

    //根据用户名查询信息
    User findArrearsById(@Param("id") Integer id);

    //更新租赁信息
    void updateArrears(Integer id, String balance);

    //删除订单
    void deleteArrears(Integer id);


    //电池累积租出数
    Integer batteryRentalCount();

    //当前电池租出数
    Integer batteryCurrentRentalCount();
}
