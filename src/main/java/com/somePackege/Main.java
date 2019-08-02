package com.somePackege;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class Main{
    public static void main(String[] args) throws Exception {

        Document doc = Jsoup.connect("https://yandex.ru/").get();


        String title = doc.title();

        System.out.println("Title: " + title);

        Elements div = doc.getElementsByClass("news__item-content");

        List<String> str = div.stream().map(element -> {
            return element.ownText();
        }).collect(Collectors.toList());


        div.stream().forEach(element -> {
            System.out.println(element.ownText());
        });


    }
}
