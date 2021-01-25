package com.example.webapp;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class Site implements Comparable<Site> {

    private Set<String> allUrlLinks = new HashSet<>();
    private Map<String, Integer> keywords = new LinkedHashMap<>();
    private String url;
    private String textFromUrl;

    public void setKeywords(String... words) {
        for (String word : words) {
            this.keywords.put(word, 0);
        }
    }

    @Override
    public int compareTo(Site s) {
        return s.getKeywords().get("Total").compareTo(getKeywords().get("Total"));
    }
}
