package com.music.utils;


import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import com.music.constant.BaiduConfig;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ToMusicManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(ToMusicDemo.class);


    // 初始化一个AipSpeech
    AipSpeech client;

    {
        client = new AipSpeech(BaiduConfig.APP_ID, BaiduConfig.API_KEY, BaiduConfig.SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }


    public void setHttpProxy(String host, int port) {
        if (client == null) {
            client = new AipSpeech(BaiduConfig.APP_ID, BaiduConfig.API_KEY, BaiduConfig.SECRET_KEY);
        }
        client.setHttpProxy(host, port);
    }

    public void setSocketProxy(String host, int port) {
        if (client == null) {
            client = new AipSpeech(BaiduConfig.APP_ID, BaiduConfig.API_KEY, BaiduConfig.SECRET_KEY);
        }
        client.setSocketProxy(host, port);
    }

    public HashMap<String, Object> getDefaultOption() {
        // 调用接口
        HashMap<String, Object> option = new HashMap<>();
        option.put("cuid", BaiduConfig.APP_ID);
        option.put("spd", BaiduConfig.OPTION_SPD);
        option.put("pit", BaiduConfig.OPTION_PIT);
        option.put("vol", BaiduConfig.OPTION_VOL);
        option.put("per", BaiduConfig.OPTION_PER);
        option.put("aue", BaiduConfig.OPTION_AUE);
        LOGGER.info("默认请求百度Option={}", option);
        return option;
    }

    public HashMap<String, Object> getDefaultOption(String cuid, String spd, String pit, String vol, String per, String aue) {
        // 调用接口
        HashMap<String, Object> option = new HashMap<>();
        option.put("cuid", cuid);
        option.put("spd", spd);
        option.put("pit", pit);
        option.put("vol", vol);
        option.put("per", per);
        option.put("aue", aue);
        LOGGER.info("自定义请求百度Option={}", option);
        return option;
    }

    public void syntheis(String content, String outpath, String filename) {

        sutheisByOption(content, outpath, filename, getDefaultOption());
    }


    public void syntheis(String content, String outpath, String filename, HashMap<String, Object> option) {

        sutheisByOption(content, outpath, filename, option);
    }


    private void sutheisByOption(String content, String outpath, String filename, HashMap<String, Object> defaultOption) {
        TtsResponse res = client.synthesis(content, "zh", 1, defaultOption);
        byte[] data = res.getData();
        JSONObject result = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, (outpath.equals("/") ? "" : outpath) + File.separator + filename);
                LOGGER.info("文件路径{}", new File((outpath.equals("/") ? "" : outpath) + File.separator + filename).getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (result != null) {
            LOGGER.info("百度响应结果 {}", res.getResult().toString());
        }
    }

}