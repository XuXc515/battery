package com.xxc.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.xxc.authentication.JWTUtil;
import com.xxc.config.AliPayConfig;
import com.xxc.config.URLConfig;
import com.xxc.domain.Battery;
import com.xxc.domain.Recharge;
import com.xxc.domain.Sale;
import com.xxc.service.AliPayService;
import com.xxc.service.BatteryService;
import com.xxc.util.AliPayUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;

/**
 * @author xxc
 * @date 2020/8/15 - 15:20
 */
@RestController
@RequestMapping("/aliPay")
public class AliPayController {

    @Resource
    private AliPayService aliPayService;

    @Resource
    private BatteryService batteryService;

    private String account;

    /**
     * <p>方法说明: TODO 支付通知(支付宝后台的回调通知)
     * <p>返回说明: String SUCCESS/FAIL
     **/
    @RequestMapping("/aliPayNotify")
    public String getPayNotify(HttpServletRequest request) throws Exception {
//        System.out.println("==========  支付宝支付回调      ==========");
        //获取支付宝POST过来反馈信息
        Map<String, String> receiveMap = getReceiveMap(request);
//        System.out.println("支付宝支付回调参数=="+receiveMap);
        // 获取支付宝的通知返回参数,可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)
        // 1,判断是否有异步通知
        if (receiveMap.get("notify_id").equals(null)) {
            System.out.println("支付宝支付回调没有异步通知");
            return "no notify message";
        }
        //2,判断是否是支付宝发来的异步通知
//        System.out.println(receiveMap.get("notify_id"));
//        System.out.println(AliPayUtil.verifyResponse(receiveMap.get("notify_id")));
//        if(!AliPayUtil.verifyResponse(receiveMap.get("notify_id")).equals("true")){
//            System.out.println("支付宝支付回调通知错误");
//            return ("response failure");
//        }
        //3,判断是否是支付宝发来的异步通知
//        System.out.println(receiveMap);
        if (!AliPayUtil.rsaCheck(receiveMap)) {
            System.out.println("支付宝支付回调验证签名失败");
            return "sign failure";
        }
        //4,判断交易状态
        String tradeStatus = receiveMap.get("trade_status");
        // 商户订单号
        String orderNo = receiveMap.get("out_trade_no");
        // 商户交易金额
        BigDecimal total_amount = new BigDecimal(receiveMap.get("total_amount"));
        // 商户交易实收金额
        BigDecimal receipt_amount = new BigDecimal(receiveMap.get("receipt_amount"));
        if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {

            boolean a = orderNo.startsWith("2");
            boolean b = orderNo.startsWith("3");
            //订单业务操作

            if (a) {     //电池订单
                try {
                    //更改数据库未支付为已支付
                    aliPayService.changeBatteryOrder(orderNo);
                    //实际交易金额等于交易金额，就表示全款交易
                    if (receipt_amount == total_amount)
                        aliPayService.expendUserBalance(account, total_amount);
                    else
                        aliPayService.expendUserBalance(account, receipt_amount);
                    return "SUCCESS";
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    return "failure";
                }
            } else if (b) {    //充值订单
                try {
                    //更改数据库未支付为已支付
                    aliPayService.changeRechargeOrder(orderNo);
                    //实际交易金额等于交易金额，就表示全款交易
                    if (receipt_amount.equals(null))
                        aliPayService.rechargeUserBalance(account, total_amount);
                    else
                        aliPayService.rechargeUserBalance(account, receipt_amount);
                    return "SUCCESS";
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    return "failure";
                }
            } else
                return "failure";
        } else {
            System.out.println("支付宝支付回调失败");
            return "failure";
        }
    }

    /**
     * <p>方法说明: TODO 获取请求参数
     * <p>返回说明: Map<String,String> receiveMap
     **/
    private static Map<String, String> getReceiveMap(HttpServletRequest request) {
        Map<String, String> receiveMap = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            receiveMap.put(name, valueStr);
        }
        return receiveMap;
    }


    /**
     * <p>方法说明: TODO 订单支付(返回支付宝订单给客户端)
     * <p>参数说明: String orderNo 订单号
     * <p>参数说明: 支付状态
     **/
    @PostMapping("/aliPay")
    public Map<String, Object> aliPay(@RequestBody JSONObject jsonObject,
                                      HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        String token = JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD"));
//        response.setHeader("TOKEN",token);

        Map<String, Object> map = new HashMap<String, Object>();

        //获取前端传来的标志数据判断是电池订单还是充值订单
        Integer signal = Integer.parseInt(jsonObject.get("signal").toString());

        //判断订单，0是电池订单，1是充值订单
        if (signal == 0) {   //电池订单

            //获取前端传来的订单数据
            account = (String) jsonObject.get("account");
            String deviceId = (String) jsonObject.get("deviceId");
            BigDecimal price = new BigDecimal(jsonObject.get("price").toString());
            String deviceName = (String) jsonObject.get("deviceName");


            Sale lastSale = aliPayService.findLastSaleId();
            Integer saleId = Integer.parseInt(lastSale.getSaleId()) + 1;

            String payStatus = "未支付";
            String payMethod = "支付宝支付";
            String orderDetail = "租赁电池";

            Sale sale = new Sale();
            sale.setSaleId(saleId.toString());
            sale.setUserAccount(account);
            sale.setBatteryId(deviceId);
            sale.setStatus(payStatus);
            sale.setPrice(price);
            sale.setPayMethod(payMethod);
            sale.setOrderDetail(orderDetail);

            aliPayService.saveBatteryOrder(sale);


//            try {
//                Battery battery = batteryService.findBatteryByDeviceId(deviceId);
//                int i = price.intValue()/battery.getBatteryPrice().intValue();
//
//                String url = URLConfig.SERVER_URL + "/bms/iotdevice/getdeviceinf";//指定URL
//
//                Map<String, Object> priceMap = new HashMap<>();//存放参数
//                priceMap.put("deviceId", deviceId);
//                priceMap.put("setUseDay", i);
//
//                JSON json = JSONUtil.parse(priceMap);
//                //发送post请求并接收响应数据
//                String result = HttpUtil.post(url, json.toString());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }


            StringBuffer totalAmount = new StringBuffer(); //订单总价值
            StringBuffer body = new StringBuffer();        //对一笔交易的具体描述信息
            StringBuffer subject = new StringBuffer();     //商品的标题/交易标题/订单标题/订单关键字等

            try {
                totalAmount.append(price);
                body.append(deviceName);
                subject.append("智能电池租赁");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String notifyUrl = AliPayUtil.getNotifyUrl(request);

            try {
                return payment(notifyUrl, saleId.toString(), totalAmount.toString(), body.toString(), subject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (signal == 1) {  //充值订单

            account = (String) jsonObject.get("account");
            BigDecimal money = new BigDecimal(jsonObject.get("money").toString());

            Recharge rechargeLast = aliPayService.findLastRechargeId();
            Integer rechargeId = Integer.parseInt(rechargeLast.getRechargeId()) + 1;

            Recharge recharge = new Recharge();
            recharge.setRechargeId(rechargeId.toString());
            recharge.setUserAccount(account);
            recharge.setPrice(money);
            //保存充值记录
            Boolean b = aliPayService.saveRechargeOrder(recharge);

            StringBuffer totalAmount = new StringBuffer(); //订单总价值
            StringBuffer body = new StringBuffer();        //对一笔交易的具体描述信息
            StringBuffer subject = new StringBuffer();     //商品的标题/交易标题/订单标题/订单关键字等

            try {
                totalAmount.append(money);
                body.append(account);
                subject.append("钱包充值");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String notifyUrl = AliPayUtil.getNotifyUrl(request);
//            System.out.println("回调地址= " + notifyUrl);

            try {
                return payment(notifyUrl, rechargeId.toString(), totalAmount.toString(), body.toString(), subject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else
            map.put("msg", "未知的订单");

        return map;
    }


    /**
     * <p>方法说明: TODO 订单支付处理(生成支付宝订单)
     * <p>参数说明: String notifyUrl 回调地址
     * <p>参数说明: String orderNo 订单号
     * <p>参数说明: String total_amount 订单总价
     **/
    private static Map<String, Object> payment(String notifyUrl, String orderNo, String totalAmount, String body, String subject) throws Exception {

        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.GATE,
                AliPayConfig.APP_ID,
                AliPayConfig.PRIVATE_KEY,
                "json",
                AliPayConfig.CHARSET,
                AliPayConfig.ALIPAY_PUBLIC_KEY,
                AliPayConfig.SIGN_TYPE);

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(orderNo);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(totalAmount);
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        Map<String, Object> map = new HashMap<>();
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);

            map.put("msg", response.getBody());

//            System.out.println("客户端请求= "+response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return map;      //返回给前端处理
        } catch (AlipayApiException e) {
            e.printStackTrace();
            map.put("msg", "生成订单出错");
            return map;
        }

    }


    /**
     *<p>方法说明: TODO 支付同步验证结果
     *<p>参数说明: String data 成功为9000
     *<p>参数说明: @return 支付状态  成功为1
     **/
//    @PostMapping("/aliPayVerification")
//    Object aliPayVerification(String data) {
//        if ("9000".equals(data)) {
//            return "支付成功";
//        } else if ("8000".equals(data)) {
//            return "正在处理中";
//        } else if ("4000".equals(data)) {
//            return "订单支付失败";
//        } else if ("5000".equals(data)) {
//            return "重复请求";
//        } else if ("6001".equals(data)) {
//            return"用户中途取消";
//        } else if ("6002".equals(data)) {
//            return "网络连接出错";
//        } else if ("6004".equals(data)) {
//            return "支付结果未知";
//        } else {
//            return "其他未知错误";
//        }
//    }
}
