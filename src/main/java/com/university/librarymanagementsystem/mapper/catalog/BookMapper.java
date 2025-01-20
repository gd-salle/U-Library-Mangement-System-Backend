package com.university.librarymanagementsystem.mapper.catalog;

import java.util.stream.Collectors;

import com.university.librarymanagementsystem.dto.catalog.BookDto;
import com.university.librarymanagementsystem.entity.catalog.Author;
import com.university.librarymanagementsystem.entity.catalog.Book;

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
                book.getSection(),
                book.getDateAcquired(),
                book.getNotes(),
                book.getLocation(),
                book.getVendor(),
                book.getFundingSource(),
                book.getSubjects());
    }

}