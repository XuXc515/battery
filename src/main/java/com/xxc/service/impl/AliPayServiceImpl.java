package com.xxc.service.impl;

import com.xxc.dao.RechargeMapper;
import com.xxc.dao.SaleMapper;
import com.xxc.dao.UserMapper;
import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import com.xxc.service.AliPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xxc
 * @date 2020/8/15 - 17:07
 */
@Service("aliPayService")
public class AliPayServiceImpl implements AliPayService {

    @Resource
    private SaleMapper saleMapper;

    @Resource
    private RechargeMapper rechargeMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Sale findLastSaleId() {
        return saleMapper.findLastSaleId();
    }

    @Override
    public void saveBatteryOrder(Sale sale) {
        saleMapper.saveBatteryOrder(sale);
    }

    @Override
    public void changeBatteryOrder(String saleId) {
        saleMapper.changeBatteryOrder(saleId);
    }

    @Override
    public Sale saleByOrderId(String saleId) {
        return saleMapper.saleByOrderId(saleId);
    }

    @Override
    public void deleteBatteryOrder(String saleId) {
        saleMapper.deleteBatteryOrder(saleId);
    }

    @Override
    public Recharge findLastRechargeId() {
        return rechargeMapper.findLastRechargeId();
    }

    @Override
    public Boolean saveRechargeOrder(Recharge recharge) {
        return rechargeMapper.saveRechargeOrder(recharge);
    }

    @Override
    public void changeRechargeOrder(String rechargeId) {
        rechargeMapper.changeRechargeOrder(rechargeId);
    }

    @Override
    public Recharge rechargeByOrderId(String rechargeId) {
        return rechargeMapper.rechargeByOrderId(rechargeId);
    }

    @Override
    public void deleteRechargeOrder(String rechargeId) {
        rechargeMapper.deleteRechargeOrder(rechargeId);
    }

    @Override
    public void rechargeUserBalance(String userAccount, BigDecimal balance) {
        userMapper.rechargeUserBalance(userAccount, balance);
    }

    @Override
    public void expendUserBalance(String userAccount, BigDecimal balance) {
        userMapper.expendUserBalance(userAccount, balance);
    }
}
