package com.example.webapp;

import org.jsoup.Jsoup;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private Site site;
    public Parser(Site site) {
        this.site = site;
    }

    void getTextFromUrl() throws IOException  {
        site.setTextFromUrl(Jsoup.connect(site.getUrl()).ignoreContentType(true).get().toString());
//        site.setTextFromUrl(Jsoup.parse(new URL(site.getUrl()), 10000).toString());
    }

    void findWord(){
        int totalCount = 0;
        for (Map.Entry<String, Integer> pointWord : site.getPointWords().entrySet()) {
            int count = 0;
            Pattern pattern = Pattern.compile("[\": ]"+pointWord.getKey()+ "[,.\":' ]");
            Matcher matcher = pattern.matcher(site.getTextFromUrl());
            while(matcher.find()) {
                count++;
            }
            site.getPointWords().put(pointWord.getKey(), count);
            totalCount += count;
        }
        site.getPointWords().put("Total", totalCount);
    }


    void findLinks() {
        Pattern patternForLinks = Pattern.compile("(?<=href=\")https?:[^\"\\\\<]+");
        Matcher matcherForLinks = patternForLinks.matcher(site.getTextFromUrl());
        while (matcherForLinks.find()) {
            String url = matcherForLinks.group();
            if(!(url.equals(site.getUrl())))
                    site.getAllUrlLinks().add(url);
        }
    }
}

