package com.xxc.service;

import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;

import java.math.BigDecimal;

/**
 * @author xxc
 * @date 2020/8/15 - 17:06
 */
public interface AliPayService {

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


    //用户充值余额
    void rechargeUserBalance(String userAccount, BigDecimal balance);

    //用户花费余额
    void expendUserBalance(String userAccount, BigDecimal balance);
}
