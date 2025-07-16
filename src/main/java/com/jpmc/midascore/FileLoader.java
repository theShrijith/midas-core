package com.jpmc.midascore;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class FileLoader {

    public String[] loadStrings(String path) {
        // Normalize path
        String normalizedPath = path.startsWith("/") ? path.substring(1) : path;
        System.out.println("📂 Attempting to load resource: " + normalizedPath);

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(normalizedPath)) {
            if (inputStream == null) {
                System.err.println("❌ File not found at: " + normalizedPath);
                System.err.println("📁 Current working directory: " + System.getProperty("user.dir"));
                return new String[0];
            }

            String fileText = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            return fileText.split("\\R"); // split on any line break
        } catch (Exception e) {
            System.err.println("❌ Error reading file: " + normalizedPath);
            e.printStackTrace();
            return new String[0];
        }
    }
}
