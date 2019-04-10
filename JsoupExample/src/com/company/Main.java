package com.company;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main{


    public static void main(String[] args) throws IOException {

        String url = "https://bitaps.com/";
        LocalDateTime lastTime =LocalDateTime.now();
        while(true){
            Document doc = Jsoup
                    .connect(url)
                    .userAgent("Jsoup client")
                    .timeout(5000).get();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            Element dollars = doc.select("span#average_last_dollars").first();
            Element cents = doc.select("small#average_last_cents").first();

            Elements percents = doc.select("div.bold.text-right.w-100.smaller");

            if(!dtf.format(now).equals(dtf.format(lastTime))) {
                System.out.println(dtf.format(now));
                System.out.println("$" + dollars.text() + "." + cents.text());
                System.out.println(percents.first().text() + "\n" + percents.next().text());
            }

            lastTime = now;
        }
    }
}