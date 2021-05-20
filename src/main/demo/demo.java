package main.demo;

import sun.security.provider.MD5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Auther ctq
 * @Date 2020/6/20
 */
public class demo {

    public static void main(String[] args) throws Exception {
        SortedMap<String, Object> params = new TreeMap<>();

        params.put("ZKK", "15");
        params.put("NONCESTR", "1522115166482");
        params.put("AT", "1525524700740");
        params.put("PASSWORD", "123456");
        params.put("PHONEID", "android");
        params.put("PHONEIP", "PHONEIP");
        params.put("USERNAME", "13800000001");
        params.put("APPID", "A80C1A90A90C4260B52CB8FE559F70BD");

        StringBuilder s1 = new StringBuilder();
        for (String key : params.keySet()) {
            s1.append(key).append("=").append(params.get(key)).append("&");
        }
        s1.deleteCharAt(s1.length() - 1);
        System.out.println(s1);
        System.out.println(params);



        String key = "9A0A8659F005D6984697E2CA0A9CF3B7";

        String stringA="appId=123456&content=TEST";

        String stringB = stringA + "&filename= CARD_APPLY_REQ_350101_20190619120000123.json";

        String stringSignTemp = stringB + "&key=" + key ;

        System.out.println(stringSignTemp);

        String sign = stringToMD5(stringSignTemp).toUpperCase();

        if("04A35ADD8F7941071C4E0F43A726DF8C".equals(sign))
        {
            System.out.println("校验成功");
        }

        System.out.println(sign);


    }

    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


}
