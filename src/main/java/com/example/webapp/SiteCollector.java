package com.example.webapp;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor
public class SiteCollector {

    private final String[] keywords;
    private int maxCrawlDepth;
    private int maxPageToFetch;
    static boolean isReadyToRecursion = true;
    static List<String> uniqueUrl = new ArrayList<String>();
    static List<Site> childSites = new ArrayList<Site>();

    public Site createNewSite(String url) throws IOException {
        Site site = new Site();
        site.setKeywords(keywords);
        site.setUrl(url);
        Parser parser = new Parser(site);
        parser.getTextFromUrl();
        parser.findKeywords();
        parser.findLinks();
        return site;
    }

    public void collectSites(Site site) throws IOException {
        for (String siteUrl : site.getAllUrlLinks()) {
            if (breakConditions()) break;
            if (uniqueUrl.contains(siteUrl)) continue;
            uniqueUrl.add(siteUrl);
            maxPageToFetch -= 1;
            Site newSite = createNewSite(siteUrl);
            childSites.add(newSite);
        }
        if (isReadyToRecursion) recursion();
    }

    public void recursion() throws IOException {
        isReadyToRecursion = false;
        maxCrawlDepth -= 1;
        List<Site> parentSites = new ArrayList<Site>(childSites);
        childSites.clear();
        if (!breakConditions()) {
            for (Site site : parentSites) {
                collectSites(site);
                if (breakConditions()) break;
            }
        }
        if (breakConditions()) WebCrawlerApp.finalSites.addAll(childSites);
        WebCrawlerApp.finalSites.addAll(parentSites);
        parentSites.clear();
        if (!breakConditions()) recursion();
        System.out.println(maxPageToFetch + " Page to fetch");
        System.out.println(maxCrawlDepth + " Deep");
    }

    public boolean breakConditions() {
        return maxPageToFetch == 0 || maxCrawlDepth == 0;
    }

}
