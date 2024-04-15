package com.mqm.HttpClient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/4/15 19:55
 */

public class HttpConfig {
    public static void main(String[] args) throws IOException, URISyntaxException {
        //创建一个httpclient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求地址
        //创建一个HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)        //创建连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)          //设置获取连接的最长时间，单位是毫秒
                .setSocketTimeout(10 * 1000)       //设置数据传输的最长时间，单位是毫秒
                .build();
        //给请求头设置信息
        httpGet.setConfig(config);
        CloseableHttpResponse execute =null;
        try {
            //使用Httpclient发起请求，获取response
            execute = httpClient.execute(httpGet);
            if (execute.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(execute.getEntity(), "utf-8");
                System.out.println(content.length());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            execute.close();
            httpClient.close();
        }
    }
}
