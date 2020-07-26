package com.kuang.utils;

import com.kuang.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.ArrayList;


/*
 *@author:C1q
 */

@Component
public class HtmlParseUtil {

    public static void main(String[] args) throws Exception {
            new HtmlParseUtil().parseJD("vue").forEach(System.out::println);

    }

    public ArrayList<Content> parseJD(String keyword) throws Exception {
        String url = "https://search.jd.com/Search?keyword="+keyword;


        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");

        Elements lis = element.getElementsByTag("li");

        ArrayList<Content> goodList = new ArrayList<>();
        for (Element li : lis) {
            //source-data-lazy-img 图片地址
            String Imgurl = li.getElementsByTag("img").eq(0).attr("source-data-lazy-img");

            String price = li.getElementsByClass("p-price").eq(0).text();
            String title = li.getElementsByClass("p-name").eq(0).text();

//            String publish = li.getElementsByClass("p-shop").eq(0).text();
            Content content = new Content();
            content.setImg(Imgurl);
            content.setPrice(price);
            content.setTitle(title);
            goodList.add(content);
        }
        return goodList;
    }
}