package main.chat;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

/**
 * @Auther ctq
 * @Date 2021/5/15
 */
public class HttpUtils {

    private static final String TENCENT_OCR_KEY = "KtQyWX6FISaBvNMK";

    public static String doPost(String url, Map<String, String> params) {
        String sign;
        try {
            sign = signForTencentOCR(params);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

        params.put("sign", sign);
        StringBuffer data = new StringBuffer();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            data.append(entry.getKey())
                    .append("=");
            try {
                data.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            data.append("&");
        }

        data.deleteCharAt(data.length() - 1);

        try {
            byte[] postDataBytes = data.toString().getBytes("UTF-8");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            conn.connect();

            StringBuilder sb = new StringBuilder();
            if (conn.getResponseCode() == 200) {
                Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                for (int c; (c = in.read()) >= 0;) {
                    sb.append((char) c);
                }
                in.close();
            } else {
                sb.append("错误码 ：")
                        .append(conn.getResponseCode())
                        .append(" 错误信息 ：")
                        .append(conn.getResponseMessage());
            }

            conn.disconnect();
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }


    }

    public static String signForTencentOCR(Map<String, String> params) throws Exception {
        final List<String> keys = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            keys.add(entry.getKey());
        }
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        StringBuffer data = new StringBuffer();
        for (String key : keys) {
            data.append(key)
                    .append("=")
                    .append(URLEncoder.encode(params.get(key), "UTF-8"))
                    .append("&");
        }

        data.append("app_key=")
                .append(TENCENT_OCR_KEY);
        return getMD5(data.toString()).toUpperCase();
    }

    /**
     * 计算MD5摘要指值
     * @param s
     * @return String
     */
    public static String getMD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // length用户要求产生字符串的长度
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
