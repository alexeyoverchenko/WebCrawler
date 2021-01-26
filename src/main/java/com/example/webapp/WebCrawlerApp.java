package com.example.webapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebCrawlerApp {

    private final static String WRITING_PATH = "/Users/antonpus/Work/MyWork/webCrawler.csv";
    private final static String SEED_PAGE = "https://en.wikipedia.org/wiki/Elon_Musk";
    public static String[] keywords = {"Tesla", "Musk", "Gigafactory", "Elon Mask"};
    static int maxPageToFetch = 100;
    static int maxCrawlDepth = 8;
    static List<Site> finalSites = new ArrayList<Site>();

    public static void main(String[] args) throws IOException {
        SiteCollector siteCollector = new SiteCollector(keywords, maxCrawlDepth, maxPageToFetch);
        Site site = siteCollector.createNewSite(SEED_PAGE);
        finalSites.add(site);
        siteCollector.collectSites(site);
        Collections.sort(finalSites);
        Utility.writeSitesToFile(WRITING_PATH, finalSites);
    }

}
