package com.xxc.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.NotYetConnectedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 开启WebSocket支持
 *
 * @author xxc
 * @date 2021/1/18 - 15:33
 */
@Slf4j
public class WebSocketConfig {
    @Resource
    WebSocketClient wClient;

    public static Map<String, String> jsonMaps = new ConcurrentHashMap<>();

    public WebSocketClient getConn(String uri, String msg) {
        try {

            WebSocketClient webSocketClient = new WebSocketClient(new URI(uri), new Draft_6455()) {

                @SneakyThrows
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("[websocket] 连接成功");
                }

                @SneakyThrows
                @Override
                public void onMessage(String message) {
                    log.info("[websocket] 收到消息={}", message);
                    if (message.equals("connect_success")) {
                        //发送数据
                        send(msg);
                    } else {
                        jsonMaps = mapStringToMap(message);
//                        if (!(jsonMaps.size() == 0))
//                            send("");
                    }

                }

                @Override
                public void send(String text) throws NotYetConnectedException {
                    super.send(text);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("[websocket] 退出连接");
                }

                @Override
                public void onError(Exception ex) {
                    log.info("[websocket] 连接错误={}", ex.getMessage());
                }
            };
            webSocketClient.connect();
//            return jsonMaps.toString();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 实现服务器主动推送
     */

    public void sendMessage(String message) throws IOException {
        wClient.send(message);
    }

    public static Map<String, String> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(", ");
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0];
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
