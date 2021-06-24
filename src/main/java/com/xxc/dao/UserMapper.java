package com.xxc.dao;

import com.xxc.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface UserMapper {

    /*
    CostService
    */
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

    /*
    UserService
     */

    //用户登录
    User userLogin(String userAccount, String userPassword);

    //查询有无同名账户
    User findBySign(String userAccount);

    //查询最后一行id
    User userIdBySign();

    //用户注册
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


    //用户充值余额
    void rechargeUserBalance(String userAccount, BigDecimal balance);

    //用户花费余额
    void expendUserBalance(String userAccount, BigDecimal balance);
}