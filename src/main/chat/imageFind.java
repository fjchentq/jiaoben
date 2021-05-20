package main.chat;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSONObject;
import utils.DHttp;
import utils.RandomUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 网络图片识别文字
 * @Auther ctq
 * @Date 2020/12/18
 */
public class imageFind {

    private static final String API_INTERFACE = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_imagetranslate";

    /**
     * 当前会话, 这个随便 ,可自定义生成
     */
    private static final String SESSION = "100001";


    /**
     * 你的app_key
     */
    private static final String APP_KEY = "0o6AXHZkInWDsw1k";

    /**
     * 你的app_id
     */
    private static final String APP_ID = "2160906455";


    public static String get(String content) {
        Map<String, String> map = new HashMap<>(6);
        map.put("app_id", APP_ID);
        map.put("session", SESSION);
        //秒级时间戳
        map.put("time_stamp", System.currentTimeMillis() / 1000 + "");
        //随机字符串 , 非空且长度上限32字节
        map.put("nonce_str", RandomUtil.random(10));
        map.put("scene","doc");
        map.put("source","auto");
        map.put("target","zh");

        String answer = "";
        try {
            //按照需要生成签名
            map = getSignature(map,APP_KEY);
            String utf08 = DHttp.sendPost(API_INTERFACE, map, "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(utf08);
            JSONObject data = jsonObject.getJSONObject("data");
            answer = data.getString("answer");
            System.out.println(utf08);
            return answer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }


    /**
     * 生成签名
     * 参考 https://blog.csdn.net/Java_Antelope/article/details/100015942
     */
    public static Map<String, String> getSignature(Map<String, String> params,String APP_KEY) throws IOException {
        Map<String, String> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        StringBuilder baseString = new StringBuilder();
        int a = 1+1;
        for (Map.Entry<String, String> param : entrys) {
            if (param.getValue() != null && !"".equals(param.getKey().trim()) && !"sign".equals(param.getKey().trim())
                    && !"".equals(param.getValue())) {
                baseString.append(param.getKey().trim()).append("=")
                        .append(URLEncoder.encode(param.getValue(), "UTF-8")).append("&");
            }
        }
        if (baseString.length() > 0) {
            baseString.deleteCharAt(baseString.length() - 1).append("&app_key=").append(APP_KEY);
        }
        try {
            //MD5使用的是 hutool
            String sign = MD5.create().digestHex(baseString.toString()).toUpperCase();
            sortedParams.put("sign", sign);
        } catch (Exception ex) {
            throw new IOException(ex);
        }
        return sortedParams;
    }

    public static void main(String[] args) {
        get("");
    }
}
