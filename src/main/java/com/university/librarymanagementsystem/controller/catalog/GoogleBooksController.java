package com.university.librarymanagementsystem.controller.catalog;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.catalog.CallNumberReq;
import com.university.librarymanagementsystem.dto.catalog.GoogleBooksDto;
import com.university.librarymanagementsystem.entity.catalog.Book;
import com.university.librarymanagementsystem.service.catalog.GoogleBooksService;
import com.university.librarymanagementsystem.service.catalog.MetadataService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/sru")
public class GoogleBooksController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @Autowired
    private MetadataService metadataService;

    @GetMapping("/googlebooks/search")
    public ResponseEntity<String> searchBooks(@RequestParam("query") String query) {
        String response = googleBooksService.searchBooks(query);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/loc/search")
    public List<Map<String, String>> getMetadata(@RequestParam String query) {
        return metadataService.retrieveMetadata(query);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody GoogleBooksDto bookDto) {
        Book savedBook = googleBooksService.saveBook(bookDto);
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(savedBook);
    }

    @PostMapping("/generate-call-number")
    public ResponseEntity<String> generateCallNumber(@RequestBody CallNumberReq request) {
        // Generate the call number using the service
        String callNumber = googleBooksService.generateCallNumber(
                request.getCategory(),
                request.getAuthors(),
                request.getPublishedDate(),
                request.getTitle());

        if ("Class number not found".equals(callNumber)) {
            return ResponseEntity.status(404).body(callNumber); // Return 404 if the class number is missing
        }

        return ResponseEntity.ok(callNumber); // Return the generated call number
    }

}