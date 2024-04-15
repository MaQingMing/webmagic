package com.mqm.HttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/4/15 19:55
 */

public class HttpPostParamTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        //创建一个httpclient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //设置请求地址
        HttpPost httpPost = new HttpPost("http://xxxxx.com");
        //声明List集合，封装表单中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //设置参数
        params.add(new BasicNameValuePair("keys","java"));
        //创建表单的Entity对象，第一个参数就是封装好的表单数据，第二个参数就是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
        httpPost.setEntity(formEntity);
        CloseableHttpResponse execute =null;
        try {
            //使用Httpclient发起请求，获取response
            execute = httpClient.execute(httpPost);
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
