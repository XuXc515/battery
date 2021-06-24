package com.xxc.util;

import com.xxc.config.WebSocketConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xxc
 * @date 2021/3/22 9:12
 */
public class ThreadUtil {

    public Map<String, String> ThreadSleep() throws InterruptedException {

        Map<String, String> map = new HashMap<>();

        Thread.sleep(1000);
        map = WebSocketConfig.jsonMaps;
        if (map.size() == 0 || map == null) {
            Thread.sleep(500);
            map = WebSocketConfig.jsonMaps;
            if (map.size() == 0 || map == null) {
                Thread.sleep(500);
                map = WebSocketConfig.jsonMaps;
                if (map.size() == 0 || map == null) {
                    Thread.sleep(500);
                    map = WebSocketConfig.jsonMaps;
                    if (map.size() == 0 || map == null) {
                        Thread.sleep(500);
                        map = WebSocketConfig.jsonMaps;
                        if (map.size() == 0 || map == null) {
                            Thread.sleep(1000);
                            map = WebSocketConfig.jsonMaps;
                            if (map.size() == 0 || map == null) {
                                Thread.sleep(1000);
                                map = WebSocketConfig.jsonMaps;
                                if (map.size() == 0 || map == null) {
                                    Thread.sleep(1000);
                                    map = WebSocketConfig.jsonMaps;
                                    if (map.size() == 0 || map == null) {
                                        Thread.sleep(1000);
                                        map = WebSocketConfig.jsonMaps;
                                        if (map.size() == 0 || map == null) {
                                            Thread.sleep(1000);
                                            map = WebSocketConfig.jsonMaps;
                                            if (map.size() == 0 || map == null) {
                                                Thread.sleep(1000);
                                                map = WebSocketConfig.jsonMaps;
                                                if (map.size() == 0 || map == null) {
                                                    Thread.sleep(1000);
                                                    map = WebSocketConfig.jsonMaps;
                                                    if (map.size() == 0 || map == null) {
                                                        Thread.sleep(10000);
                                                        map = WebSocketConfig.jsonMaps;
                                                        if (map.size() == 0 || map == null) {
                                                            map.put("status", "wait");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return map;
    }
}
