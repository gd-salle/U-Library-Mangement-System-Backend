package com.university.librarymanagementsystem.repository.catalog;

import java.util.List;

import com.university.librarymanagementsystem.dto.catalog.BookSearchRequest;
import com.university.librarymanagementsystem.entity.catalog.Book;

public interface BookRepositoryCustom {

    List<Book> advancedSearchBooks(BookSearchRequest request);
}
