package main.chat;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.MultiValueMap;
import utils.DHttp;
import utils.RandomUtil;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Auther ctq
 * @Date 2020/12/17
 */
public class Chat {


    private static final String API_INTERFACE = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";

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
        map.put("question",content);
        //秒级时间戳
        map.put("time_stamp", System.currentTimeMillis() / 1000 + "");
        //随机字符串 , 非空且长度上限32字节
        map.put("nonce_str", RandomUtil.random(10));
        String answer = "";
        try {
            //按照需要生成签名
            map = getSignature(map);
            String utf08 = DHttp.sendPost(API_INTERFACE, map, "utf-8");
            //System.out.println(utf08);
            JSONObject jsonObject = JSONObject.parseObject(utf08);
            JSONObject data = jsonObject.getJSONObject("data");
            answer = data.getString("answer");
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
    public static Map<String, String> getSignature(Map<String, String> params) throws IOException {
        Map<String, String> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        StringBuilder baseString = new StringBuilder();
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
        //System.out.println(get("我想你不想我，你想我不想你。").length());

        List<String> strings = new ArrayList<>();
        strings.add("");

        String a = "好无聊啊";

        while (true)
        {
            a = get(a);
            if(a.length()!=0 && !a.equals(strings.get(strings.size()-1)))
            {
                strings.add(a);
            }
            else {
                a = strings.get(randomCommon(0,strings.size()-1));
                System.out.println("随机"+a);
            }
            System.out.println(a);
        }
    }

    public static int randomCommon(int min, int max){
        return  (int) (Math.random() * (max - min)) + min;
    }
}
