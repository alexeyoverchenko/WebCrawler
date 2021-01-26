package com.example.webapp;

import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Parser {

    private final Site site;

    void getTextFromUrl() throws IOException {
        String data = Jsoup.connect(site.getUrl())
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                .get()
                .toString();
        if (data != null) {
            site.setTextFromUrl(data);
        }
    }

    void findKeywords() {
        int totalCount = 0;
        for (Map.Entry<String, Integer> pointWord : site.getKeywords().entrySet()) {
            int count = 0;
            Pattern pattern = Pattern.compile("[\": ]" + pointWord.getKey() + "[,.\":' ]");
            Matcher matcher = pattern.matcher(site.getTextFromUrl());
            while (matcher.find()) {
                count++;
            }
            site.getKeywords().put(pointWord.getKey(), count);
            totalCount += count;
        }
        site.getKeywords().put("Total", totalCount);
    }

    void findLinks() {
        Pattern patternForLinks = Pattern.compile("(?<=href=\")http:[^\"\\\\<]+");
        Matcher matcherForLinks = patternForLinks.matcher(site.getTextFromUrl());
        while (matcherForLinks.find()) {
            String url = matcherForLinks.group();
            if (!(url.equals(site.getUrl())))
                site.getAllUrlLinks().add(url);
        }
    }
}
