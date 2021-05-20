package utils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class DHttp {
	 public static String httpRequest(String requestUrl,String requestMethod,String outputStr){  
	        StringBuffer buffer=null;  
	        try{  
	        URL url=new URL(requestUrl);  
	        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        conn.setDoOutput(true);  
	        conn.setDoInput(true);  
	        conn.setRequestMethod(requestMethod);  
	        conn.connect();  
	        //往服务器端写内容 也就是发起http请求需要带的参数  
	        if(null!=outputStr){  
	            OutputStream os=conn.getOutputStream();  
	            os.write(outputStr.getBytes("utf-8"));  
	            os.close();  
	        }
	        //读取服务器端返回的内容  
	        InputStream is=conn.getInputStream();  
	        InputStreamReader isr=new InputStreamReader(is,"utf-8");  
	        BufferedReader br=new BufferedReader(isr);  
	        buffer=new StringBuffer();  
	        String line=null;  
	        while((line=br.readLine())!=null){  
	            buffer.append(line);  
	        }  
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }
	        return buffer.toString();  
	    }

	public static String sendPost(String urlParam, Map<String, String> params, String charset) {
		StringBuffer resultBuffer = null;
		// 构建请求参数
		StringBuffer sbParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> e : params.entrySet()) {
				sbParams.append(e.getKey());
				sbParams.append("=");
				sbParams.append(e.getValue());
				sbParams.append("&");
			}
		}
		URLConnection con = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		try {
			URL realUrl = new URL(urlParam);
			// 打开和URL之间的连接
			con = realUrl.openConnection();
			// 设置通用的请求属性
			con.setRequestProperty("accept", "*/*");
			con.setRequestProperty("connection", "Keep-Alive");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			con.setDoOutput(true);
			con.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			osw = new OutputStreamWriter(con.getOutputStream(), charset);
			if (sbParams != null && sbParams.length() > 0) {
				// 发送请求参数
				osw.write(sbParams.substring(0, sbParams.length() - 1));
				// flush输出流的缓冲
				osw.flush();
			}

			//读取服务器端返回的内容
			InputStream is=con.getInputStream();
			InputStreamReader isr=new InputStreamReader(is,"utf-8");
			br=new BufferedReader(isr);
			resultBuffer=new StringBuffer();
			String line=null;
			while((line=br.readLine())!=null){
				resultBuffer.append(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					osw = null;
					throw new RuntimeException(e);
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				}
			}
		}
		return resultBuffer.toString();
	}
}
