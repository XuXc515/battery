package com.xxc.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxc.authentication.JWTUtil;
import com.xxc.config.BatteryConfig;
import com.xxc.domain.*;
import com.xxc.service.BackManagerService;
import com.xxc.service.BatteryService;
import com.xxc.service.CostService;
import com.xxc.service.UserService;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xxc
 * @date 2021/1/13 - 9:29
 */
@RestController
@RequestMapping("/userTest")
public class UserTestController {

    @Resource
    private UserService userService;

    @Resource
    private BackManagerService backManagerService;

    @Resource
    private BatteryService batteryService;

    @Resource
    private CostService costService;

    private static String URL = "http://8.129.79.195:8099";

    //用户注册
    @PostMapping("/sign")
    public Map<String, Object> userSign(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println("sign进来了");
        String account = (String) jsonObject.get("account");
        String password = (String) jsonObject.get("password");
        //判断是否有同名用户名
        User user = userService.findBySign(account);
        BackManager manager = backManagerService.findManagerByAccount(account);
        //没有则进行注册
        if (user == null && manager == null) {
            User userLast = userService.userIdBySign();
            Integer userId = Integer.parseInt(userLast.getUserId()) + 1;
            boolean b = userService.userSign(userId, account, password);
            if (b)
                map.put("msg", "注册成功");
            else
                map.put("msg", "注册失败");
        } else {
            map.put("msg", "用户名已存在");
        }

        return map;
    }

    //用户登录
    @PostMapping("/login")
    public Map<String, Object> userLogin(@RequestBody JSONObject jsonObject, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println("login进来了");
        String account = (String) jsonObject.get("account");
        String password = (String) jsonObject.get("password");


//        System.out.println(password);
        //根据用户名和密码获取用户
        User user = userService.userLogin(account, password);

        if (user == null)
            map.put("msg", "用户名或密码错误");
        else {
            map.put("msg", "登录成功");
            map.put("user", user);
        }
        // 生成 Token
        response.setHeader("TOKEN", JWTUtil.sign(account, password));

        return map;
    }

    //用户修改个人头像
    @GetMapping("/userPhoto/{account}")
    public Map<String, Object> userPhoto(@PathVariable("account") String account,
                                         HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

//        System.out.println("userPhoto进来了");

        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.findUserByAccount(account);

        if (user != null) {

            if (!user.getUserPhoto().equals(null)) {

                byte[] data = null;

                // 读取图片字节数组
                try {
                    InputStream in = new FileInputStream(user.getUserPhoto());
                    data = new byte[in.available()];
                    in.read(data);
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 对字节数组Base64编码
                BASE64Encoder encoder = new BASE64Encoder();
                String str = encoder.encode(data);
//            System.out.println(str);
                map.put("userPhoto", str);
            } else
                map.put("msg", "头像为空");
        } else {
            map.put("msg", "用户名不存在");
        }

        return map;
    }

    //用户修改个人信息
    @PostMapping("/changeSelf")
    public Map<String, Object> userChangeSelf(@RequestBody JSONObject jsonObject,
                                              HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

//        System.out.println("change进来了");
        Map<String, Object> map = new HashMap<String, Object>();

        String account = (String) jsonObject.get("account");
        String password = (String) jsonObject.get("password");
        String name = (String) jsonObject.get("name");
        String sex = (String) jsonObject.get("sex");
        String mobile = (String) jsonObject.get("mobile");
        String address = (String) jsonObject.get("address");
        String userPhoto = (String) jsonObject.get("userPhoto");

//        System.out.println("解码前" + userPhoto);

        String imgFilePath = "/opt/battery/images/" + account + ".jpg";

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(userPhoto);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setUserAccount(account);
        user.setUserName(name);
        user.setUserPassword(password);
        user.setSex(sex);
        user.setMobile(mobile);
        user.setAddress(address);
        user.setUserPhoto(imgFilePath);

        boolean b = userService.changeSelf(user);

        if (b)
            map.put("msg", "修改成功");
        else
            map.put("msg", "修改失败");

        return map;
    }

    //全部电池类型信息
    @GetMapping("/batteryTypeInfo")
    public Map<String, Object> batteryTypeInfo(HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

//        System.out.println("batteryTypeInfo进来了");
        Map<String, Object> map = new HashMap<String, Object>();

        //获取电池类型信息
        List<BatteryType> list = batteryService.findAllBatteryType();

        if (list != null)
            map.put("list", list);
        else
            map.put("msg", "没有电池类型信息");

        return map;
    }

    //全部电池状态信息
    @GetMapping("/batteryStatusInfo")
    public Map<String, Object> batteryStatusInfo(HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

//        System.out.println("batteryStatusInfo进来了");
        Map<String, Object> map = new HashMap<String, Object>();

        //获取电池状态信息
        List<BatteryStatus> list = batteryService.findAllBatteryStatus();

        if (list != null)
            map.put("list", list);
        else
            map.put("msg", "没有电池状态信息");

        return map;
    }

    //根据id查询电池所有信息
    @GetMapping("/findBatteryInfoById/{deviceId}")
    public Map<String, Object> findBatteryInfoById(@PathVariable("deviceId") String deviceId,
                                                   HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
//        System.out.println("findBatteryInfoById进来了");
        Map<String, Object> map = new HashMap<String, Object>();

        //获取电池状态信息
        Battery battery = batteryService.findBatteryInfoById(deviceId);

//        System.out.println("拿到数据了"+battery);

        if (battery != null)
            map.put("battery", battery);
        else
            map.put("msg", "没有电池信息");

        return map;
    }

    //查询用户自己的电池
    @PostMapping("/userBattery")
    public Map<String, Object> userBattery(@RequestBody JSONObject jsonObject,
                                           HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
//        System.out.println("userBattery进来了");
        Map<String, Object> map = new HashMap<String, Object>();
        String account = (String) jsonObject.get("account");
        List<Sale> list = costService.findBatteryIdByAccount(account);

        if (list != null)
            map.put("list", list);
        else
            map.put("msg", "没有电池信息");

        return map;
    }

    //电池故障上报
    @PostMapping("/addBatteryFault")
    public Map<String, Object> addBatteryRepair(@RequestBody JSONObject jsonObject,
                                                HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        Map<String, Object> map = new HashMap<String, Object>();
        String deviceId = (String) jsonObject.get("deviceId");
        String faultDetail = (String) jsonObject.get("faultDetail");

        Battery battery = batteryService.findBatteryByDeviceId(deviceId);

        if (battery != null)
            if (!battery.getDeviceRepair().equals("维修中")) {
                batteryService.addBatteryRepair(deviceId, faultDetail);
                map.put("msg", "上报成功");
            } else
                map.put("msg", "电池故障已上报");
        else
            map.put("msg", "电池不存在");

        return map;
    }

    @PostMapping("/crash")
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

//        System.out.println("crash come in");

        InputStream is = request.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        String result = "";
        try {
            result = saveFile(dis);
        } catch (Exception e) {
            e.printStackTrace();
            result = "uploadError";
        }

        request.getSession().invalidate();
        response.setContentType("text/html;charset=UTF-8");
        ObjectOutputStream dos = new ObjectOutputStream(
                response.getOutputStream());
        dos.writeObject(result);
        dos.flush();
        dos.close();
        dis.close();
        is.close();
    }

    /**
     * 保存文件
     *
     * @param dis
     * @return
     */
    private String saveFile(DataInputStream dis) {

//        System.out.println("saveFile come in");

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");//设置日期格式
        //System.out.println(df.format(new Date())); new Date()为获取当前系统时间

        String fileUrl = "/opt/battery/crash/" + df.format(new Date()) + ".log";
        File file = new File(fileUrl);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fps = null;
        try {
            fps = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int length = -1;

        try {
            while ((length = dis.read(buffer)) != -1) {
                fps.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fps.flush();
            fps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


    //查询用户自己的所有订单
    @GetMapping("/userOrder/{account}")
    public Map<String, Object> userOrder(@PathVariable("account") String account,
                                         HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
//        System.out.println("userOrder进来了");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Sale> saleList = userService.findSaleByAccount(account);

        List<Recharge> rechargeList = userService.findRechargeByAccount(account);

        if (saleList != null)
            map.put("saleList", saleList);
        else
            map.put("msg1", "没有电池租赁订单信息");

        if (rechargeList != null)
            map.put("rechargeList", rechargeList);
        else
            map.put("msg2", "没有账户充值订单信息");

        return map;
    }

    //根据订单号查询用户自己的订单
    @GetMapping("/orderInfo/{orderId}")
    public Map<String, Object> orderInfo(@PathVariable("orderId") String orderId,
                                         HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));
//        System.out.println("orderInfo进来了");
        Map<String, Object> map = new HashMap<String, Object>();

        Sale sale = userService.findSaleBySaleId(orderId);
        Recharge recharge = userService.findRechargeByRechargeId(orderId);

        if (sale == null)
            if (recharge == null)
                map.put("msg", "该订单编号无效");
            else
                map.put("order", recharge);
        else
            map.put("order", sale);

        return map;
    }

    @PostMapping("/getBatteryTime")
    public String getBatteryTime(@RequestBody JSONObject jsonObject,
                                 HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        String jsonString = jsonObject.toString();

        String[] batteryString = BatteryConfig.BATTERYSTRING;
        cn.hutool.json.JSONObject json = JSONUtil.createObj();

        HashMap map = JSON.parseObject(jsonString, HashMap.class);

        String deviceId = null;

        for (Object key : map.keySet()) {
            for (int i = 0; i < batteryString.length; i++) {
                if (key == batteryString[i])
                    json.set(key.toString(), map.get(key));
                else if (key == "deviceId") {
                    deviceId = map.get(key).toString();
                    json.set(key.toString(), map.get(key));
                }
            }
        }

//        System.out.println(json.toString());

        cn.hutool.json.JSONObject start = JSONUtil.createObj()
                .set("devElect", "1")
                .set("deviceId", deviceId);

//        System.out.println(start.toString());

        String url = URL + "/getdata/getdeviceinf";//指定URL

        String result = HttpRequest
                .post(url)
                .header("Content-Type", "application/json")
                .body(start.toString())
                .execute()
                .body();

        System.out.println("发送电池启动标志后返回的结果：" + result);

        //发送get请求并接收响应数据
        String data = HttpRequest
                .post(url)
                .header("Content-Type", "application/json")
                .body(json.toString())
                .execute()
                .body();

        System.out.println("获取电池时长的结果：" + data);

        return data;
    }

    @PostMapping("/setBatteryTime")
    public String setBatteryTime(@RequestBody JSONObject jsonObject,
                                 HttpServletRequest request, HttpServletResponse response) {
        // 生成 Token
//        response.setHeader("TOKEN",JWTUtil.sign(request.getHeader("NAME"), request.getHeader("PWD")));

        String jsonString = jsonObject.toString();

        String[] batteryString = BatteryConfig.BATTERYSTRING;
        cn.hutool.json.JSONObject json = JSONUtil.createObj();

        HashMap map = JSON.parseObject(jsonString, HashMap.class);

        for (Object key : map.keySet()) {
            for (int i = 0; i < batteryString.length; i++) {
                if (key == batteryString[i])
                    json.set(key.toString(), map.get(key));
                else if (key == "deviceId")
                    json.set(key.toString(), map.get(key));
            }
        }

        String url = URL + "/getdata/setdeviceinf";//指定URL

        //发送get请求并接收响应数据
        String data = HttpRequest
                .post(url)
                .header("Content-Type", "application/json")
                .body(json.toString())
                .execute()
                .body();

        System.out.println("设置电池时长的结果：" + data);

        return data;
    }
}
