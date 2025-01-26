package com.university.librarymanagementsystem.service.catalog;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class CutterSanbornService {

    private Map<String, String> cutterMap;

    @PostConstruct
    public void init() {
        try {
            // Load the JSON file from the classpath
            File file = new ClassPathResource("static/cutter-data.json").getFile();
            ObjectMapper objectMapper = new ObjectMapper();
            cutterMap = objectMapper.readValue(file, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading Cutter numbers from file", e);
        }
    }

    public String generateCutterNumber(String prefix) {
        String cutterNumber = cutterMap.get(prefix.toLowerCase());
        System.out.println("THE PREFIX: " + prefix);
        if (cutterNumber == null) {
            cutterNumber = "99"; // Default fallback if not found
        }
        return cutterNumber;
    }
}
