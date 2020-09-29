package com.example.webapp;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SiteCollector {

    private static List<Site> sites = new ArrayList<Site>();
    private static List<String> uniqueUrl = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        String url = "https://en.wikipedia.org/wiki/Elon_Musk";
        String[] pointWords = {"Tesla", "Musk", "Gigafactory", "Elon Mask"};

        Site site = createNewSite(url, pointWords);
        collectSites(site, pointWords);
        writeSitesToFile();

    }

    public static Site createNewSite(String url, String[] pointWords) throws IOException {
            Site site = new Site();
            site.setPointWords(pointWords);
            site.setUrl(url);
            Parser parser = new Parser(site);
            parser.getTextFromUrl();
            parser.findWord();
            parser.findLinks();
            sites.add(site);
            return site;
    }

    public static void collectSites(Site site, String[] pointWords) throws IOException {
                for (String siteUrl : site.getAllUrlLinks()) {
                    if (uniqueUrl.contains(siteUrl)) continue;
                    if (sites.size() == 10) return;
                    uniqueUrl.add(siteUrl);
                    Site newSite = createNewSite(siteUrl, pointWords);
                    collectSites(newSite, pointWords);
        }
    }

    public static void writeSitesToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("C:/Users/admin/Desktop/webCrawler.txt", true);
        StringBuilder stringBuilder;

        for (Site nextSite : sites) {
            stringBuilder = new StringBuilder(nextSite.getUrl() + " ");
            for (Map.Entry<String, Integer> pointWord : nextSite.getPointWords().entrySet()) {
                stringBuilder.append(pointWord.getValue()).append(" ");
            }
            fileWriter.write(stringBuilder.toString() + "\r\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

}
