package com.xxc.dao;

import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RechargeMapper {

    /*
    CostService
     */
    //分页查询用户充值记录
    List<Recharge> queryPageRecharge(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountRecharge();

    //根据日期查询用户充值信息
    List<Recharge> findByDateRecharge(@Param("curr") int curr, @Param("limit") int limit, Date start, Date end, String userAccount);

    int findByDateRechargeCount(Date start, Date end, String userAccount);

    //查询充值记录最后一行id
    Recharge findLastRechargeId();

    //保存充值记录
    Boolean saveRechargeOrder(Recharge recharge);

    //支付成功，更改订单状态
    void changeRechargeOrder(String rechargeId);

    //根据订单编号获取订单信息
    Recharge rechargeByOrderId(String rechargeId);

    //支付失败，删除订单
    void deleteRechargeOrder(String rechargeId);

    /*
    UserService
     */
    //根据用户account查询充值订单
    List<Recharge> findRechargeByAccount(String userAccount);

    //根据订单编号查询租赁订单
    Recharge findRechargeByRechargeId(String rechargeId);
}