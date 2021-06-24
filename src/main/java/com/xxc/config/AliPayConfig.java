package com.xxc.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author xxc
 * @date 2020/8/15 - 14:21
 */
@Configuration
public class AliPayConfig {

    /**
     * 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
     */
    public static final String SELLER_ID = "2088712305316774";

    /**
     * 支付宝网关
     * https://mapi.alipay.com/gateway.do?service=notify_verify&
     */
    public static final String GATE = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
     */
    public static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLrOihZ/2cWkt7PPS+UMCUR/na6svOAvse7++y1eSbI7DHhnmsQuApX56+jpRHaCdQNyixz4jSjc9VLEIzJUyzJdO+a7ttmy3OSn4rtYm8ptEkAfp8tGyG9fabdWWTaqHRBZThEi5ghMfQxhmOxgoQk3PGgNsJ6H4Nn5P37CQtN2bkLajwz8sZXrjpHitOBchg963WRjPt/doVqcc1OIoLjscN24gH6D1pFF98LXbnYily9yVivYCPRLb3Naw6UFlS3rCCDVag3NtjID61po2//mvhjjC7kAmdFIhjNgcF+xbVSDMgxKLs78dYMtZe7z56awLcWD4AilliCxZZvGddAgMBAAECggEAUs4meBITi08Isr4XhoCPOxlUqhFK1A52XAf4/ffKeKL3GcLCfXqENGMI1IXoqFC6Bdc9m/Sic9k4kpETigjR6N9DIf/ydO9rf4IwPK96QcpP8N3vZ2D7eiN1mQJHPtcXqbh6Y9iIgBYX89CNtFP6AYu5NnSL5trbaC4z5eYsKFVs+61Knr+K4hfge7XzleieD5bu/7g8erXZx6cia86YxTyHyYr1fT6z7TEk5Ip/Ku18FIJd9Vtrby5wDSI0F52p/L4RJ2yxlwGKpQG5A+U2kmLEvNPBfV3eTf8KwF2duySQSIGgOJYJQHncm6ogtQW4uPOAG9Kkny0n++i3aT8BYQKBgQDjPk0Zd7b1KYevmc8d1EG4rcIq0C6Kw7hoURqkTfzMNpFs/yC8XJCIL7Plom/KUKaI/o3NXL2eFk9kH+W/wYtPObOlqGIL/PL6F3Uih4v3EdOJni7H4A97QE+XRdtyQGBhxEZhUgMfMYO7usbNc2roXPkoWMhvaVyBuMmTLGYjBQKBgQCdWclMrkMXzWNUsKGSDyYf667PbnCcMs1AmrutVKR+yJZYxa+2eKpzXk6xHuH2HKl2BaKlDnhuPwrZHq6Q09E2OnSAzVyqaZgS+PmNHfPvT4+xm0XADEwoDNzrsHS+w8ipjhrzmdT1qHcFoPLmv687Z9bGo7lXOf5BjTISihmSeQKBgA6YJLl6/8wVA7raZPvkcugaeLrpLeQJl+FTPCPLQm4XHxXh+MdAJqQodCROX2Gd9mQb/R1wVFk/0495oPbExl6rNQdxngJpF23KbeJu1CVlBrHy78w0bqoawkHT4bkfu1DsGx0K+Fpbgrtezn/JZtdPiUz3DsBhAujt5jxGgLTFAoGAEQZs/GoCgmnLrJcr9DTFaEDE+la1woDQqoPTeK6m2bz0X4iMOY8WykLS+uEfi9SQFWAIuBk3uJ4Dm3hxZOQ2R8rNXj5aH2ordSoRPk6WXTdbkdj+FAiAmPjDUpEWIKnoZUgl7GOhoi7nKem/Pm5RFHloyLdgT2rL8uXHHDBrEekCgYEArgbxd0oELGzpNUMPXTntJCSAj7ykKiCJFbXJFy2S5Kh1W0DFPiIAYz+ssSESIPR0lbAmcCbY2m7jUirn+nip3TROMJyfUjE6dQETeK3SmlUIzfgpeS033nbvshOfjPPe2zbaWXpr4lqKUSREjgdv0hr7IUaDIhu0W2O6szWVUIM=";

    /**
     * 应用公钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
     */
    public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi6zooWf9nFpLezz0vlDAlEf52urLzgL7Hu/vstXkmyOwx4Z5rELgKV+evo6UR2gnUDcosc+I0o3PVSxCMyVMsyXTvmu7bZstzkp+K7WJvKbRJAH6fLRshvX2m3Vlk2qh0QWU4RIuYITH0MYZjsYKEJNzxoDbCeh+DZ+T9+wkLTdm5C2o8M/LGV646R4rTgXIYPet1kYz7f3aFanHNTiKC47HDduIB+g9aRRffC1252IpcvclYr2Aj0S29zWsOlBZUt6wgg1WoNzbYyA+taaNv/5r4Y4wu5AJnRSIYzYHBfsW1UgzIMSi7O/HWDLWXu8+emsC3Fg+AIpZYgsWWbxnXQIDAQAB";

    /**
     * 支付宝的公钥，查看地址：https://openhome.alipay.com/platform/keyManage.htm?keyType=partner
     */
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgZ2RRv4sG/uvaM9AN6b6Av0qWTwuqovPKvBua8FW7GC8T+ce6bpjsEtfIxzzDnrhfxG1cOuDQ/ZEQNYkFWg0hmhsdZHe25KcEIlyxmyZR5xCQnKPijO2pOMJqYG4GHQTV5l+wiQ8/4HeS5r4/2+uT4NvCvZsXGQnKmz6KI4d5kV2SuW3wpMuP/Vh6L1Ne2BOAOvMnO9rssVSnrdavbtfa4es4YQxepka7K3CL4lstrtlHyyfz3jglCntzUsPy9qAQ11DYVkzcMbJQCoWWKYPqGBkp8bz8P6OG8cVYS6EL3UmhsBk8Ano6k4JhOL1EWsTPNppTmQD6JJEJ40IZyuuEwIDAQAB";

    /**
     * 签名方式
     */
    public static final String SIGN_TYPE = "RSA2";
    /**
     * 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法
     */
    public static final String LOG_PATH = "E:/支付宝开发助手/日志";
    /**
     * 字符编码格式 目前支持 gbk 或 utf-8
     */
    public static final String CHARSET = "utf-8";
    /**
     * 接收通知的接口名(回调地址)
     */
    public static final String NOTIFY_URL = "/aliPay/aliPayNotify";
    /**
     * APPID
     */
    public static final String APP_ID = "2021000119681440";
}
