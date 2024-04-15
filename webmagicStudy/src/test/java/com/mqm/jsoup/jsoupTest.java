package com.mqm.jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * @author mqm
 * @version 1.0
 * @date 2024/4/15 21:55
 */
public class jsoupTest {


    @Test
    public void jsoupUrltest() throws Exception{
        //解析URL地址，第一个参数是访问的URL，第二个参数是访问时候的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 2000);
        //使用标签选择器，获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testString() throws Exception{
        //使用工具类读取文件，获取字符串
        String content = FileUtils.readFileToString(new File("c:\\Users\\xxx\\test.html"));
        //解析字符串
        Document doc = Jsoup.parse(content);
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testData() throws Exception{
        Document doc = Jsoup.parse(new File("c:\\Users\\xxx\\test.html"), "utf-8");
        Element element = doc.getElementById("test");

        //元素中获取元素
        //1.从元素中获取id
        String id = element.id();
        //2.元素中获取classname
        String className = element.className();
        //3.从元素中获取所有属性attributes
        Attributes attributes = element.attributes();
        //4.从元素中获取文本内容text
        String text = element.text();
    }
    @Test
    public void testSelector() throws Exception{
        Document doc = Jsoup.parse(new File("c:\\Users\\xxx\\test.html"), "utf-8");
        Elements span = doc.select("span");
        for (Element element : span) {
            System.out.println(element.text());
        }
        Element test = doc.select("#test").first();
        Elements select = doc.select(".class");
        Elements select1 = doc.select("[attriutes]");
    }

    @Test
    public void testSelector2() throws Exception{
        //组合查找
        Document doc = Jsoup.parse(new File("c:\\Users\\xxx\\test.html"), "utf-8");
        //el#id :元素+ID ，比如:  span#test
        //el.class:元素+class，比如:li.class_a
        //el[attr]:元素+属性名,比如:span[abc]//任意组合:比如: span[abc].s_name
        //ancestor child:查找某个元素下子元素，比如: .city_con li 查找"city_con“下的所有li
        //parent > child:查找某个父元素下的直接子元素，比如:

        Elements span = doc.select("span#test");
        for (Element element : span) {
            System.out.println(element.text());
        }
    }



}
