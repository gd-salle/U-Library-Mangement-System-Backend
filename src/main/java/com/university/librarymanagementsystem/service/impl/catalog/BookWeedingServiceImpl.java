package com.university.librarymanagementsystem.service.impl.catalog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.entity.catalog.Book;
import com.university.librarymanagementsystem.service.catalog.BookWeedingService;

@Service
public class BookWeedingServiceImpl implements BookWeedingService {

    @Override
    public List<Book> getBooksForWeeding(String condition) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooksForWeeding'");
    }

}
