package com.university.librarymanagementsystem.service;

import java.util.List;

import com.university.librarymanagementsystem.dto.BookDto;

public interface BookService {

    List<BookDto> getAllBooks();
}
