package com.mqm.HttpClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/4/15 20:42
 */
public class HttpClientPoolTest {
    public static void main(String[] args) throws IOException {
        //创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        connectionManager.setMaxTotal(100);

        //设置每个主机的最大连接数
        connectionManager.setDefaultMaxPerRoute(10);

        //使用连接池管理器发起请求  两个方法请求的是两个对象
        doGet(connectionManager);
        doGet(connectionManager);

    }

    private static void doGet(PoolingHttpClientConnectionManager connectionManager) throws IOException {
        //不是创建新的httpClient,而是从连接池中获取HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
        HttpGet httpGet = new HttpGet("http://itcast.cn");

        CloseableHttpResponse response =null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content.length());
            }
        }catch (Exception e){
            if (response!=null){
                response.close();
            }
            //不能关闭httpClient,由连接池管理

        }

    }
}
