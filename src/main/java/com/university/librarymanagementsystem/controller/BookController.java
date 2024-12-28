package com.university.librarymanagementsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.BookDto;
import com.university.librarymanagementsystem.entity.Book;
import com.university.librarymanagementsystem.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/adminuser/")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("all-books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("books-by-author")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@RequestParam String authorName) {
        return ResponseEntity.ok(bookService.getBooksByAuthorName(authorName));
    }

    @GetMapping("/last-accession-number")
    public ResponseEntity<String> getLastAccessionNumber(@RequestParam String locationPrefix) {
        try {
            String lastAccessionNumber = bookService.getLastAccessionNumber(locationPrefix);
            return ResponseEntity.ok(lastAccessionNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching last accession number");
        }
    }

}
