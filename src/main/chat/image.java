package main.chat;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;
import utils.DHttp;
import utils.RandomUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @Auther ctq
 * @Date 2021/5/14
 */
public class image {

    private static final String APP_ID = "2172811384";

    public static void main(String[] args) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("app_id", String.valueOf(APP_ID));
        params.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));
        params.put("nonce_str", HttpUtils.getRandomString(15));
        params.put("image", imgString("C:\\Users\\ctq520\\Desktop\\xxproject\\1.PNG"));
       // params.put("session_id", HttpUtils.getRandomString(15));
        String result = HttpUtils.doPost("https://api.ai.qq.com/fcgi-bin/ocr/ocr_generalocr", params);
        System.out.println(result);
    }

    public static String imgString(String filePath) {
        byte[] data;
        try {
            data = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return Base64Util.encode(data);
    }

}
