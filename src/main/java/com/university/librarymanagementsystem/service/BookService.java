package com.university.librarymanagementsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.university.librarymanagementsystem.dto.BookDto;
import com.university.librarymanagementsystem.dto.BookSearchRequest;

public interface BookService {

    List<BookDto> getAllBooks();

    List<BookDto> getBooksByAuthorName(String authorName);

    String getLastAccessionNumber(String locationPrefix);

    BookDto getBookByBarcode(String barcode);

    List<BookDto> advancedSearchBooks(BookSearchRequest request);
}
