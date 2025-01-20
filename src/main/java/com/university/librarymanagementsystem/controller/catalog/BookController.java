package com.university.librarymanagementsystem.controller.catalog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.catalog.BookDto;
import com.university.librarymanagementsystem.dto.catalog.BookSearchRequest;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.service.catalog.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/adminuser")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all-books")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books-by-author")
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

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<BookDto> getBookByBarcode(@PathVariable String barcode) {
        try {
            BookDto book = bookService.getBookByBarcode(barcode);
            return ResponseEntity.ok(book);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/advance-search")
    public ResponseEntity<List<BookDto>> advancedSearch(@RequestBody BookSearchRequest request) {
        List<BookDto> books = bookService.advancedSearchBooks(request);
        return ResponseEntity.ok(books);
    }
}
