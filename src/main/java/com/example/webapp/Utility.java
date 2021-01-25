package com.example.webapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Utility {
    public static void writeSitesToFile(String WRITING_PATH, List<Site> finalSites) throws IOException {
        FileWriter fileWriter = new FileWriter(WRITING_PATH, true);
        fileWriter.write("Site, \"Tesla\", \"Musk\", \"Gigafactory\", \"Elon Mask\", \"Total\" \r\n");
        for (int i = 0; i < 10; i++) {
            StringBuilder stringBuilder = new StringBuilder(finalSites.get(i).getUrl() + " ");
            for (Map.Entry<String, Integer> pointWord : finalSites.get(i).getKeywords().entrySet()) {
                if (!pointWord.getKey().equals("Total")) {
                    stringBuilder.append(pointWord.getValue()).append(", ");
                } else {
                    stringBuilder.append(pointWord.getValue()).append("\r\n");
                    fileWriter.write(stringBuilder.toString());
                }
            }
        }
        fileWriter.flush();
        fileWriter.close();
    }
}
