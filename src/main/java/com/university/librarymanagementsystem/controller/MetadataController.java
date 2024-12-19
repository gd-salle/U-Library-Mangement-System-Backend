package com.university.librarymanagementsystem.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.service.MetadataService;

@RestController
@RequestMapping("/sru/loc-books")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping("/search")
    public List<Map<String, String>> getMetadata(@RequestParam String query) {
        return metadataService.retrieveMetadata(query);
    }
}
