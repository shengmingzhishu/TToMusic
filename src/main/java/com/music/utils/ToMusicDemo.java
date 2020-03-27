package com.music.utils;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

public class ToMusicDemo {

    private final static Logger LOGGER = LoggerFactory.getLogger(ToMusicDemo.class);

    //设置APPID/AK/SK
    public static final String APP_ID = "19079698";
    public static final String API_KEY = "OGukf2S7NalsXGCU9mer0O3Q";
    public static final String SECRET_KEY = "u1koC84kIC5rBg2nMDXl5I0ZGSsX2dqX";

    public static void main(String[] args) {
        LOGGER.info("这是一个测试");
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "log4j.properties");

        // 调用接口
        HashMap<String, Object> option = new HashMap<>();
        option.put("cuid", "19079698");
        option.put("spd", "8");
        option.put("pit", "15");
        option.put("vol", "10");
        option.put("per", "1");
        option.put("aue", "6");

        LOGGER.info("请求百度Option {}", option);

        TtsResponse res = client.synthesis("你好百度", "zh", 1, option);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "/output.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString());
        }

    }
}