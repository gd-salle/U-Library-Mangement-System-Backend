package com.university.librarymanagementsystem.controller.catalog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.catalog.BookDto;
import com.university.librarymanagementsystem.dto.catalog.BookSearchRequest;
import com.university.librarymanagementsystem.entity.catalog.Book;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.catalog.BookMapper;
import com.university.librarymanagementsystem.repository.catalog.BookRepository;
import com.university.librarymanagementsystem.repository.catalog.BookRepositoryCustom;
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
    @Autowired
    private BookRepository bookRepository;

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

    @GetMapping("/latest-accession")
    public ResponseEntity<String> getLatestAccession(
            @RequestParam String title,
            @RequestParam String isbn10,
            @RequestParam String isbn13,
            @RequestParam String locationPrefix) {
        try {
            // Fetch the latest accession number using the service
            String accessionNo = bookService.getLatestAccessionNo(title, isbn10, isbn13, locationPrefix);

            if ("NOTFOUND".equals(accessionNo)) {
                // Return 404 if no book matches the query
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOTFOUND");
            }

            return ResponseEntity.ok(accessionNo);
        } catch (Exception e) {
            // Return 500 if an error occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while fetching the latest accession number.");
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
    public List<BookDto> advancedSearchBooks(@RequestBody BookSearchRequest request) {
        List<Book> books = bookRepository.advancedSearchBooks(request);
        return books.stream().map(BookMapper::toDto).toList();
    }

    @GetMapping("/last-added-accession")
    public ResponseEntity<String> getLastAddedBookAccessionNumber() {
        try {
            String lastAccessionNumber = bookService.fetchLastAccessionNumber();
            return ResponseEntity.ok(lastAccessionNumber);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while fetching the last added book's accession number.");
        }
    }

}
