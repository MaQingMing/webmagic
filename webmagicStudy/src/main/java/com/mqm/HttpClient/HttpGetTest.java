package com.mqm.HttpClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/4/15 19:55
 */

public class HttpGetTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        //创建一个httpclient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求地址
        //创建URIBulider,动态拼接参数
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        //设置参数
        uriBuilder.setParameter("keys","java");
        //创建一个HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());
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
