package com.university.librarymanagementsystem.service.catalog;

import java.util.List;

import com.university.librarymanagementsystem.entity.catalog.Book;

public interface BookWeedingService {

    List<Book> getBooksForWeeding(String condition);

}
