package com.university.librarymanagementsystem.service.catalog;

import java.time.LocalDate;
import java.util.List;

import com.university.librarymanagementsystem.dto.catalog.BookDto;
import com.university.librarymanagementsystem.dto.catalog.BookSearchRequest;

public interface BookService {

    List<BookDto> getAllBooks();

    List<BookDto> getBooksByAuthorName(String authorName);

    String getLastAccessionNumber(String locationPrefix);

    BookDto getBookByBarcode(String barcode);

    List<BookDto> advancedSearchBooks(BookSearchRequest request);
}
