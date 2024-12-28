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

    @Override
    public List<BookDto> getBooksByAuthorName(String authorName) {
        List<Book> books = bookRepository.findBooksByAuthorName(authorName);
        return books.stream().map(BookMapper::toDto).toList();
    }

    @Override
    public String getLastAccessionNumber(String locationPrefix) {
        List<Book> books = bookRepository.findBooksByAccessionNo(locationPrefix);
        if (books.isEmpty()) {
            return locationPrefix + "-000000";
        }

        // Extract the last accession number from the books
        int maxNumber = books.stream()
                .map(Book::getAccessionNo)
                .map(accessionNo -> {
                    // Use regex to extract the numeric part before 'c.x'
                    String numericPart = accessionNo.split(" ")[0].replace(locationPrefix + "-", "");
                    try {
                        return Integer.parseInt(numericPart);
                    } catch (NumberFormatException e) {
                        return 0; // Fallback for invalid formats
                    }
                })
                .max(Integer::compare)
                .orElse(0);

        // Format the new accession number
        return String.format("%s-%06d", locationPrefix, maxNumber);
    }

}
