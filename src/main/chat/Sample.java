package main.chat;

import com.baidu.aip.ocr.AipOcr;

import java.util.HashMap;
import org.json.JSONObject;

/**
 * @Auther ctq
 * @Date 2021/5/15
 */

public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "24172741";
    public static final String API_KEY = "7oUHXN1z5nagTtMEH2KPGCKN";
    public static final String SECRET_KEY = "CgqPec9ywGv17i0H3iP6YdZGB7296g8Y";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
       // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\ctq520\\Desktop\\xxproject\\1.PNG";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString());

    }
}
