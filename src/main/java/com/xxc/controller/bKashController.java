package com.xxc.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xxc
 * @date 2020/8/19 - 9:04
 */
@RestController
@RequestMapping("/bKash")
public class bKashController {

    private static String URL = "{base_URL}";

    private String TOKEN;

    private String REFRESH_TOKEN;

    //获取bKash token
    public String getToken() {


        String url = URL + "/tokenized/checkout/token/grant";

        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("username", "username");
        headers.put("password", "password");

        Map<String, Object> map = new HashMap<>();
        map.put("app_key", "test_app_key");
        map.put("app_secret", "test_app_secret");

        //发送请求，获取返回值
        String result = HttpUtil.createPost(url).addHeaders(headers).form(map).execute().body();

//            获取token值和token的刷新值
        TOKEN = JSONUtil.parseObj(result).get("id_token").toString();
        REFRESH_TOKEN = JSONUtil.parseObj(result).get("refresh_token").toString();

        return TOKEN;
//
//
//            String url = URL + "/tokenized/checkout/token/refresh";
//
//            HashMap<String, String> headers = new HashMap<>();
//            headers.put("Content-Type", "application/json");
//            headers.put("Accept", "application/json");
//            headers.put("username", "username");
//            headers.put("password", "password");
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("app_key", "test_app_key");
//            map.put("app_secret", "test_app_secret");
//            map.put("refresh_token", refreshToken);
//
//            //发送请求，获取返回值
//            String result = HttpUtil.createPost(url).addHeaders(headers).form(map).execute().body();
//
//            //获取token值和token的刷新值
//            TOKEN = JSONUtil.parseObj(result).get("id_token").toString();
//            REFRESH_TOKEN = JSONUtil.parseObj(result).get("refresh_token").toString();
//            return TOKEN;


    }

    //更新bKash token
//    public String refreshToken(String refresh_token){
//
//        String url = URL + "/tokenized/checkout/token/refresh";
//
//        HashMap<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        headers.put("Accept", "application/json");
//        headers.put("username", "username");
//        headers.put("password", "password");
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("app_key", "test_app_key");
//        map.put("app_secret", "test_app_secret");
//        map.put("refresh_token", refresh_token);
//
//        //发送请求，获取返回值
//        String result = HttpUtil.createPost(url).addHeaders(headers).form(map).execute().body();
//
//        //获取过期时间
//        int expires = Integer.parseInt(JSONUtil.parseObj(result).get("expires_in").toString());
//
//        //获取token值和token的刷新值
//        String id_token = JSONUtil.parseObj(result).get("id_token").toString();
//        String refreshToken = JSONUtil.parseObj(result).get("refresh_token").toString();
//
//        return refreshToken(refreshToken);
//    }


    //创建付款
    @PostMapping("/createPayment")
    public Map<String, Object> createPayment(@RequestBody JSONObject jsonObject,
                                             HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
//            response.setHeader("TOKEN", token);

        String bKashToken = getToken();
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("authorization", bKashToken);
        headers.put("x-app-key", "x-app-key");


        String url = URL + "/tokenized/checkout/create";
        Map<String, Object> data = new HashMap<>();
        data.put("mode", 0011);
        data.put("payerReference", "01723888888");
        data.put("callbackURL", "yourDomain.com");
        data.put("amount", 500);
        data.put("currency", "BDT");
        data.put("intent", "sale");
        data.put("merchantInvoiceNumber", "Inv0124");

        //发送请求，获取返回值
        String result = HttpUtil.createPost(url).addHeaders(headers).form(data).execute().body();

        //付款ID 创建时间 付款金额 发票编号 bkashURL callbackURL
        String paymentID = JSONUtil.parseObj(result).get("paymentID").toString();
        String paymentCreateTime = JSONUtil.parseObj(result).get("paymentCreateTime").toString();
        String amount = JSONUtil.parseObj(result).get("amount").toString();
        String merchantInvoiceNumber = JSONUtil.parseObj(result).get("merchantInvoiceNumber").toString();
        String bkashURL = JSONUtil.parseObj(result).get("bkashURL").toString();
        String callbackURL = JSONUtil.parseObj(result).get("callbackURL").toString();

        //返回前端
        Map<String, Object> map = new HashMap<>();
        map.put("paymentID", paymentID);
        map.put("paymentCreateTime", paymentCreateTime);
        map.put("amount", amount);
        map.put("merchantInvoiceNumber", merchantInvoiceNumber);
        map.put("bkashURL", bkashURL);
        map.put("callbackURL", callbackURL);

        return map;
    }

    //执行付款
    @PostMapping("/executePayment")
    public Map<String, Object> executePayment(@RequestBody JSONObject jsonObject,
                                              HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
//            response.setHeader("TOKEN", token);

        String bKashToken = getToken();
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Accept", "application/json");
        headers.put("authorization", bKashToken);
        headers.put("x-app-key", "x-app-key");

        String paymentId = (String) jsonObject.get("paymentId");
        String url = URL + "/tokenized/checkout/execute/";
        Map<String, Object> data = new HashMap<>();
        data.put("paymentId", paymentId);

        //发送请求，获取返回值
        String result = HttpUtil.createPost(url).addHeaders(headers).form(data).execute().body();

        //付款ID 创建时间 更新时间 交易ID 交易状态
        String createTime = JSONUtil.parseObj(result).get("createTime").toString();
        String updateTime = JSONUtil.parseObj(result).get("updateTime").toString();
        String trxID = JSONUtil.parseObj(result).get("trxID").toString();
        String transactionStatus = JSONUtil.parseObj(result).get("transactionStatus").toString();

        Map<String, Object> map = new HashMap<>();
        map.put("createTime", createTime);
        map.put("updateTime", updateTime);
        map.put("trxID", trxID);
        map.put("transactionStatus", transactionStatus);

        return map;
    }

    //查询付款
    @PostMapping("/queryPayment")
    public Map<String, Object> queryPayment(@RequestBody JSONObject jsonObject,
                                            HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
//            response.setHeader("TOKEN", token);

        String bKashToken = getToken();
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("authorization", bKashToken);
        headers.put("x-app-key", "x-app-key");

        String paymentID = (String) jsonObject.get("paymentID");

        String url = URL + "/tokenized/checkout/payment/status";
        Map<String, Object> data = new HashMap<>();
        data.put("paymentID", paymentID);

        //发送请求，获取返回值
        String result = HttpUtil.createPost(url).addHeaders(headers).form(data).execute().body();

        //付款ID 创建时间 更新时间 交易ID 交易状态
        String paymentCreateTime = JSONUtil.parseObj(result).get("paymentCreateTime").toString();
        String paymentExecuteTime = JSONUtil.parseObj(result).get("paymentExecuteTime").toString();
        String transactionStatus = JSONUtil.parseObj(result).get("transactionStatus").toString();
        String userVerificationStatus = JSONUtil.parseObj(result).get("userVerificationStatus").toString();

        Map<String, Object> map = new HashMap<>();
        map.put("paymentCreateTime", paymentCreateTime);
        map.put("paymentExecuteTime", paymentExecuteTime);
        map.put("transactionStatus", transactionStatus);
        map.put("userVerificationStatus", userVerificationStatus);

        return map;
    }

    //搜索交易
    @PostMapping("/searchTransaction")
    public Map<String, Object> searchTransaction(@RequestBody JSONObject jsonObject,
                                                 HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
//            response.setHeader("TOKEN", token);

        String bKashToken = getToken();
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Accept", "application/json");
        headers.put("authorization", bKashToken);
        headers.put("x-app-key", "x-app-key");

        String trxID = (String) jsonObject.get("trxID");

        String url = URL + "/tokenized/checkout/payment/status";
        Map<String, Object> data = new HashMap<>();
        data.put("trxID", trxID);

        //发送请求，获取返回值
        String result = HttpUtil.createPost(url).addHeaders(headers).form(data).execute().body();

        //创建时间 完成时间 交易状态 交易类型 交易金额 交易ID 用户手机
        String initiationTime = JSONUtil.parseObj(result).get("initiationTime").toString();
        String completedTime = JSONUtil.parseObj(result).get("completedTime").toString();
        String transactionStatus = JSONUtil.parseObj(result).get("transactionStatus").toString();
        String transactionType = JSONUtil.parseObj(result).get("transactionType").toString();
        String amount = JSONUtil.parseObj(result).get("amount").toString();
        String trxIDResponse = JSONUtil.parseObj(result).get("trxID").toString();
        String customerMsisdn = JSONUtil.parseObj(result).get("customerMsisdn").toString();

        Map<String, Object> map = new HashMap<>();
        map.put("initiationTime", initiationTime);
        map.put("completedTime", completedTime);
        map.put("transactionStatus", transactionStatus);
        map.put("transactionType", transactionType);
        map.put("amount", amount);
        map.put("trxID", trxIDResponse);
        map.put("customerMsisdn", customerMsisdn);

        return map;
    }

    //退款交易
    @PostMapping("/refundTransaction")
    public Map<String, Object> refundTransaction(@RequestBody JSONObject jsonObject,
                                                 HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//            String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
//            response.setHeader("TOKEN", token);

        String bKashToken = getToken();
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("Accept", "application/json");
        headers.put("authorization", bKashToken);
        headers.put("x-app-key", "x-app-key");

        String paymentID = (String) jsonObject.get("paymentID");
        String amount = (String) jsonObject.get("amount");
        String trxID = (String) jsonObject.get("trxID");
        String sku = (String) jsonObject.get("sku");
        String reason = (String) jsonObject.get("reason");

        String url = "{{tokenized_url}}/{{api_version}}/tokenized/checkout/payment/refund";
        Map<String, Object> data = new HashMap<>();
        data.put("paymentID", paymentID);
        data.put("amount", amount);
        data.put("trxID", trxID);
        data.put("sku", sku);
        data.put("reason", reason);

        //发送请求，获取返回值
        String result = HttpUtil.createPost(url).addHeaders(headers).form(data).execute().body();

        //完成时间 交易状态 原始交易ID 退款交易ID 退款金额 货币 收费
        String completedTime = JSONUtil.parseObj(result).get("completedTime").toString();
        String transactionStatus = JSONUtil.parseObj(result).get("transactionStatus").toString();
        String originalTrxID = JSONUtil.parseObj(result).get("originalTrxID").toString();
        String refundTrxID = JSONUtil.parseObj(result).get("refundTrxID").toString();
        String amountResponse = JSONUtil.parseObj(result).get("amount").toString();
        String currency = JSONUtil.parseObj(result).get("currency").toString();
        String charge = JSONUtil.parseObj(result).get("charge").toString();

        Map<String, Object> map = new HashMap<>();
        map.put("completedTime", completedTime);
        map.put("transactionStatus", transactionStatus);
        map.put("originalTrxID", originalTrxID);
        map.put("refundTrxID", refundTrxID);
        map.put("amount", amountResponse);
        map.put("currency", currency);
        map.put("charge", charge);

        return map;
    }

}
