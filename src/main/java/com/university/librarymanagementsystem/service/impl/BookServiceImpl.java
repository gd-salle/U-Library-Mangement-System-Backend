package com.university.librarymanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.BookDto;
import com.university.librarymanagementsystem.entity.Book;
import com.university.librarymanagementsystem.mapper.BookMapper;
import com.university.librarymanagementsystem.repository.BookRepository;
import com.university.librarymanagementsystem.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(BookMapper::toDto).toList();
    }

}
