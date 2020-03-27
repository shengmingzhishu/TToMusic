package com.music.boot;

import com.music.constant.BaiduConfig;
import com.music.utils.ToMusicManager;

import java.util.HashMap;

public class MainDemo {


    public static void main(String[] args) {
        /**
         * 默认配置
         */
        ToMusicManager toMusicManager = new ToMusicManager();
        toMusicManager.syntheis("你好百度", "/", "demo.mp3");
        /**
         * 自定义配置
         */
        ToMusicManager toMusicManager2 = new ToMusicManager();
        //    spd	String	语速，取值0-9，默认为5中语速	否
        //    pit	String	音调，取值0-9，默认为5中语调	否
        //    vol	String	音量，取值0-15，默认为5中音量	否
        //    per	String	发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女	否
        //    aue	选填	3为mp3格式(默认)； 4为pcm-16k；5为pcm-8k；6为wav（内容同pcm-16k）; 注意aue=4或者6是语音识别要求的格式，但是音频内容不是语音识别要求的自然人发音，所以识别效果会受影响。
        HashMap<String, Object> defaultOption = toMusicManager2.getDefaultOption(BaiduConfig.APP_ID, "7", "9", "10", "4", "6");
        toMusicManager2.syntheis("你好百度", "/", "demo2.wav", defaultOption);
    }
}
