package com.example.webapp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Site {

    private HashSet<String> allUrlLinks = new HashSet<String>();
    private LinkedHashMap<String, Integer> pointWords = new LinkedHashMap<String, Integer>();
    private String url;
    private String textFromUrl;

    public String getTextFromUrl() {
        return textFromUrl;
    }

    public void setTextFromUrl(String textFromUrl) {
        this.textFromUrl = textFromUrl;
    }

    public HashSet<String> getAllUrlLinks() {
        return allUrlLinks;
    }

    public HashMap<String, Integer> getPointWords() {
        return pointWords;
    }

    public String getUrl() {
        return url;
    }

    public void setPointWords(String...words) {
        for (String word : words) {
            this.pointWords.put(word, 0);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
