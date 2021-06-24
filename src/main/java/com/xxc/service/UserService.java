package com.xxc.service;

import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import com.xxc.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xxc
 * @date 2020/8/4 - 20:15
 */
public interface UserService {

    //用户登录
    User userLogin(String userAccount, String userPassword);

    //用户注册
    User findBySign(String userAccount);

    User userIdBySign();

    boolean userSign(Integer userId, String userAccount, String userPassword);

    //用户修改个人信息
    boolean changeSelf(User user);

    //分页查询
    List<User> queryPageUser(@Param("curr") int curr, @Param("limit") int limit);

    int queryPageCountUser();

    //根据id查询用户信息
    User findUserById(Integer id);

    //根据id查询用户信息
    User findUserByAccount(String userAccount);

    //根据起止日期查询用户信息
    List<User> findUserByDate(@Param("curr") int curr, @Param("limit") int limit, Date start, Date end, String userId);

    int findUserByDateCount(Date start, Date end, String userId);

    //更新用户信息
    Boolean updateUser(User user);

    //保存用户
    void saveUser(User user);

    //删除用户
    void deleteUser(Integer id);

    //批量删除
    void deleteAll(String[] ids);


    //查询用户总数
    Integer userCount();

    //查询激活用户总数
    Integer userActiveCount();

    //查询欠款用户总数
    Integer userArrearsCount();


    //根据用户account查询租赁订单
    List<Sale> findSaleByAccount(String userAccount);

    //根据订单编号查询租赁订单
    Sale findSaleBySaleId(String saleId);

    //根据用户account查询充值订单
    List<Recharge> findRechargeByAccount(String userAccount);

    //根据订单编号查询充值订单
    Recharge findRechargeByRechargeId(String rechargeId);


}
