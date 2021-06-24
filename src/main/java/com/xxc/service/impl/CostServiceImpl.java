package com.xxc.service.impl;

import com.xxc.dao.RechargeMapper;
import com.xxc.dao.SaleMapper;
import com.xxc.dao.UserMapper;
import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import com.xxc.domain.User;
import com.xxc.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xxc
 * @date 2020/7/31 - 15:29
 */
@Service("costService")
public class CostServiceImpl implements CostService {

    @Resource
    private SaleMapper saleMapper;

    @Resource
    private RechargeMapper rechargeMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ListOperations<String, Object> listOperations;

    @Override
    public List<Sale> queryPageCost(int curr, int limit) {
        curr = (curr - 1) * limit;
        return saleMapper.queryPageCost(curr, limit);
    }

    @Override
    public int queryPageCountCost() {
        return saleMapper.queryPageCountCost();
    }

    @Override
    public List<Sale> findBySaleIdAndNameCost(int curr, int limit, String saleId, String userName) {
        curr = (curr - 1) * limit;
        return saleMapper.findBySaleIdAndNameCost(curr, limit, saleId, userName);
    }

    @Override
    public int findBySaleIdAndNameCostCount(String saleId, String userName) {
        return saleMapper.findBySaleIdAndNameCostCount(saleId, userName);
    }

    @Override
    public List<Recharge> queryPageRecharge(int curr, int limit) {
        curr = (curr - 1) * limit;
        return rechargeMapper.queryPageRecharge(curr, limit);
    }

    @Override
    public int queryPageCountRecharge() {
        return rechargeMapper.queryPageCountRecharge();
    }

    @Override
    public List<Recharge> findByDateRecharge(int curr, int limit, Date start, Date end, String userAccount) {
        curr = (curr - 1) * limit;
        return rechargeMapper.findByDateRecharge(curr, limit, start, end, userAccount);
    }

    @Override
    public int findByDateRechargeCount(Date start, Date end, String userAccount) {
        return rechargeMapper.findByDateRechargeCount(start, end, userAccount);
    }

    @Override
    public List<User> queryPageArrearsUser(int curr, int limit) {
        curr = (curr - 1) * limit;
        return userMapper.queryPageArrearsUser(curr, limit);
    }

    @Override
    public int queryPageCountArrearsUser() {
        return userMapper.queryPageCountArrearsUser();
    }

    @Override
    public List<User> findArrearsUserByUserIdAndAccount(int curr, int limit, String userId, String userAccount) {
        curr = (curr - 1) * limit;
        return userMapper.findArrearsUserByUserIdAndAccount(curr, limit, userId, userAccount);
    }

    @Override
    public int findArrearsUserByUserIdAndAccountCount(String userId, String userAccount) {
        return userMapper.findArrearsUserByUserIdAndAccountCount(userId, userAccount);
    }


    @Override
    public User findArrearsById(Integer id) {
        return userMapper.findArrearsById(id);
    }

    @Override
    public void updateArrears(Integer id, String balance) {
        userMapper.updateArrears(id, balance);
    }

    @Override
    public void deleteArrears(Integer id) {
        userMapper.deleteArrears(id);
    }

    @Override
    public List<Sale> findBatteryIdByAccount(String userAccount) {
//        return saleMapper.findBatteryIdByAccount(userAccount);
        String batteryAccount = userAccount + "battery";
        List<Sale> sales = (List<Sale>) listOperations.leftPop(batteryAccount);
        if (sales == null) {
            List<Sale> saleList = saleMapper.findBatteryIdByAccount(userAccount);
            listOperations.leftPush(batteryAccount, saleList);
            return saleList;
        } else
            return sales;
    }

    @Override
    public Integer batteryRentalCount() {
        return saleMapper.batteryRentalCount();
    }

    @Override
    public Integer batteryCurrentRentalCount() {
        return saleMapper.batteryCurrentRentalCount();
    }
}
