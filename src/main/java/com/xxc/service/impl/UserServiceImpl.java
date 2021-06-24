package com.xxc.service.impl;

import com.xxc.dao.RechargeMapper;
import com.xxc.dao.SaleMapper;
import com.xxc.dao.UserMapper;
import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import com.xxc.domain.User;
import com.xxc.service.UserService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author xxc
 * @date 2020/8/4 - 20:15
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RechargeMapper rechargeMapper;

    @Resource
    private SaleMapper saleMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Resource
    private ListOperations<String, Object> listOperations;

    @Override
    public User userLogin(String userAccount, String userPassword) {
//        return userMapper.userLogin(userAccount,userPassword);
        User user = (User) valueOperations.get(userAccount);
        if (user == null) {
            User userLogin = userMapper.userLogin(userAccount, userPassword);
            valueOperations.set(userAccount, userLogin);
            return userLogin;
        } else
            return user;
    }

    @Override
    public User findBySign(String userAccount) {

        User user = (User) valueOperations.get(userAccount);
        if (user == null) {
            User userBySign = userMapper.findBySign(userAccount);
            valueOperations.set(userAccount, userBySign);
            return userBySign;
        } else
            return user;
//        return userMapper.findBySign(userAccount);
    }

    @Override
    public User userIdBySign() {
        return userMapper.userIdBySign();
    }

    @Override
    public boolean userSign(Integer userId, String userAccount, String userPassword) {
        return userMapper.userSign(userId, userAccount, userPassword);
    }

    @Override
    public boolean changeSelf(User user) {
        return userMapper.changeSelf(user);
    }

    @Override
    @Async
    public List<User> queryPageUser(int curr, int limit) {
        curr = (curr - 1) * limit;
        return userMapper.queryPageUser(curr, limit);
    }

    @Override
    public int queryPageCountUser() {
        return userMapper.queryPageCountUser();
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findUserByAccount(String userAccount) {
        return userMapper.findUserByAccount(userAccount);
    }

    @Override
    public List<User> findUserByDate(int curr, int limit, Date start, Date end, String userId) {
        curr = (curr - 1) * limit;
        return userMapper.findUserByDate(curr, limit, start, end, userId);
    }

    @Override
    public int findUserByDateCount(Date start, Date end, String userId) {
        return userMapper.findUserByDateCount(start, end, userId);
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void deleteAll(String[] ids) {
        userMapper.deleteAll(ids);
    }

    @Override
    public Integer userCount() {
        return userMapper.userCount();
    }

    @Override
    public Integer userActiveCount() {
        return userMapper.userActiveCount();
    }

    @Override
    public Integer userArrearsCount() {
        return userMapper.userArrearsCount();
    }

    @Override
    public List<Sale> findSaleByAccount(String userAccount) {
//        return saleMapper.findSaleByAccount(userAccount);
        String saleAccount = userAccount + "sale";
//        List<Sale> sales = (List<Sale>) listOperations.leftPop(saleAccount);
        List<Sale> sales = (List<Sale>) redisTemplate.opsForValue().get(saleAccount);
        if (sales == null) {
            List<Sale> saleList = saleMapper.findSaleByAccount(userAccount);
//            listOperations.leftPush(saleAccount,saleList);
            redisTemplate.opsForValue().set(saleAccount, saleList);
            return saleList;
        } else
            return sales;
    }

    @Override
    public Sale findSaleBySaleId(String saleId) {
//        return saleMapper.findSaleBySaleId(saleId);
        Sale sale = (Sale) valueOperations.get(saleId);
        if (sale == null) {
            Sale saleBySaleId = saleMapper.findSaleBySaleId(saleId);
            valueOperations.set(saleId, saleBySaleId);
            return saleBySaleId;
        } else
            return sale;
    }

    @Override
    public List<Recharge> findRechargeByAccount(String userAccount) {
//        return rechargeMapper.findRechargeByAccount(userAccount);
        String rechargeAccount = userAccount + "recharge";
//        List<Recharge> recharges = (List<Recharge>) listOperations.leftPop(rechargeAccount);
        List<Recharge> recharges = (List<Recharge>) redisTemplate.opsForValue().get(rechargeAccount);
        if (recharges == null) {
            List<Recharge> rechargeList = rechargeMapper.findRechargeByAccount(userAccount);
//            listOperations.leftPush(rechargeAccount,rechargeList);
            redisTemplate.opsForValue().set(rechargeAccount, rechargeList);
            return rechargeList;
        } else
            return recharges;
    }

    @Override
    public Recharge findRechargeByRechargeId(String rechargeId) {
//        return rechargeMapper.findRechargeByRechargeId(rechargeId);
        Recharge recharge = (Recharge) valueOperations.get(rechargeId);
        if (recharge == null) {
            Recharge rechargeByRechargeId = rechargeMapper.findRechargeByRechargeId(rechargeId);
            valueOperations.set(rechargeId, rechargeByRechargeId);
            return rechargeByRechargeId;
        } else
            return recharge;
    }

}
