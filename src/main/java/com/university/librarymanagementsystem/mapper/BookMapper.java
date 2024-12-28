package com.university.librarymanagementsystem.mapper;

import java.util.stream.Collectors;

import com.university.librarymanagementsystem.dto.BookDto;
import com.university.librarymanagementsystem.entity.Author;
import com.university.librarymanagementsystem.entity.Book;

public class BookMapper {

    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAccessionNo(),
                book.getAuthors().stream().map(Author::getName).collect(Collectors.toList()),
                book.getPublisher(),
                book.getPublishedDate(),
                book.getDescription(),
                book.getPageCount(),
                book.getCategories(),
                book.getLanguage(),
                book.getIsbn10(),
                book.getIsbn13(),
                book.getThumbnail(),
                book.getPrintType(),
                book.getStatus(),
                book.getBarcode(),
                book.getCallNumber(),
                book.getPurchasePrice(),
                book.getCirculationType(),
                book.getDateAcquired(),
                book.getNotes(),
                book.getLocation(),
                book.getVendor(),
                book.getFundingSource(),
                book.getSubjects());
    }

}