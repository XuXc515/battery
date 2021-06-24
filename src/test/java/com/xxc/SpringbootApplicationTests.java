package com.xxc;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.xxc.config.URLConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SpringBootTest
class SpringbootApplicationTests {


    @Test
    void contextLoads() throws Exception {

//        String json="{'name':'angui','age':'23','like':'吃水果'}";
//
//        Map<String,Object> map = new HashMap<String,Object>();
//
//        try {
//            org.json.JSONObject jsonObject = new org.json.JSONObject(json);
//            Iterator iterator = jsonObject.keys();
//
//            while(iterator.hasNext()){
//                String key = (String) iterator.next();
//
//                map.put(key,jsonObject.getString(key));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println(map);

//        BigDecimal b=new BigDecimal(10.00);
//
//        int a = b.intValue();
//
//        String c = "10.00";
//
//        BigDecimal bd=new BigDecimal(c);
//
//        System.out.println(a);
//        System.out.println(bd.intValue());

//        String url = URLConfig.SERVER_URL + "/bms/iotdevice/getdeviceinf";//指定URL
//
//        Map<String, Object> priceMap = new HashMap<>();//存放参数
//        priceMap.put("deviceId", "12123");
//        priceMap.put("setUseDay", 10);
//
//        JSON json = JSONUtil.parse(priceMap);
//
//        //发送post请求并接收响应数据
//        String result = HttpUtil.post(url, json.toString());

//        byte[] data = null;
//
//        // 读取图片字节数组
//        try {
//            InputStream in = new FileInputStream("D:/IntelliJ IDEA/battery/src/main/resources/images/www.jpg");
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 对字节数组Base64编码
//        BASE64Encoder encoder = new BASE64Encoder();
//
//        System.out.println(encoder.encode(data));
    }


}
