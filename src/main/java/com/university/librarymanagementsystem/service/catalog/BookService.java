package com.university.librarymanagementsystem.service.catalog;

import java.util.List;

import com.university.librarymanagementsystem.dto.catalog.BookDto;
import com.university.librarymanagementsystem.dto.catalog.WeedInfoDTO;

public interface BookService {

    List<BookDto> getAllBooks();

    List<BookDto> getBooksByAuthorName(String authorName);

    String getLastAccessionNumber(String locationPrefix);

    BookDto getBookByBarcode(String barcode);

    String getLatestAccessionNo(String title, String isbn10, String isbn13, String locationPrefix);

    String fetchLastAccessionNumber();

    void weedBook(WeedInfoDTO weedInfoDTO);

}
