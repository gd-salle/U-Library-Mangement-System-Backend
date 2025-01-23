package com.university.librarymanagementsystem.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.BookDto;
import com.university.librarymanagementsystem.dto.BookSearchRequest;
import com.university.librarymanagementsystem.entity.Book;
import com.university.librarymanagementsystem.mapper.BookMapper;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
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

    @Override
    public BookDto getBookByBarcode(String barcode) {
        Book book = bookRepository.findByBarcode(barcode)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + barcode));

        return BookMapper.toDto(book);
    }

    @Override
    public List<BookDto> advancedSearchBooks(BookSearchRequest request) {
        // Parse year range into LocalDate
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (request.getYearRange() != null && !request.getYearRange().isEmpty()) {
            String[] years = request.getYearRange().split("-");
            try {
                startDate = LocalDate.parse(years[0].trim() + "-01-01");
                endDate = LocalDate.parse(years[1].trim() + "-12-31");
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid year range format. Use YYYY-YYYY.");
            }
        }

        // Process criteria fields
        String title = getValueForField(request.getCriteria(), "ti"); // 'ti' for title
        String authorName = getValueForField(request.getCriteria(), "au"); // 'au' for author
        String publisher = getValueForField(request.getCriteria(), "publisher");
        String isbn10 = getValueForField(request.getCriteria(), "isbn10");
        String isbn13 = getValueForField(request.getCriteria(), "isbn13");

        // Extract selected options (itemType, sections, and collection) from request
        List<String> itemType = request.getItemType();
        List<String> sections = request.getSections();
        List<String> collection = request.getCollection();

        // Call repository method with the extracted and parsed parameters
        List<Book> books = bookRepository.advancedSearchBooks(
                request.getKeyword(),
                title,
                authorName,
                publisher,
                isbn10,
                isbn13,
                collection,
                sections,
                itemType,
                request.getLanguage(),
                startDate,
                endDate,
                request.getIsAvailableOnly(),
                request.getIndividualLibrary(),
                request.getSortOrder());

        System.out.println("Keyword: " + request.getKeyword());
        System.out.println("Title: " + title);
        System.out.println("Author: " + authorName);
        System.out.println("Publisher: " + publisher);
        System.out.println("ISBN-10: " + isbn10);
        System.out.println("ISBN-13: " + isbn13);
        System.out.println("Collection: " + collection);
        System.out.println("Sections: " + sections);
        System.out.println("Item Type: " + itemType);
        System.out.println("Language: " + request.getLanguage());
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Is Available Only: " + request.getIsAvailableOnly());
        System.out.println("Individual Library: " + request.getIndividualLibrary());
        System.out.println("Sort Order: " + request.getSortOrder());

        // Convert entities to DTOs
        return books.stream()
                .map(BookMapper::toDto)
                .toList();
    }

    /**
     * Utility method to extract search term for a specific field from the criteria
     * list.
     * 
     * @param criteria List of search criteria from the request.
     * @param idx      The index (field identifier) to look for, e.g., 'ti' for
     *                 title or 'au' for author.
     * @return The search term for the given field, or null if not present.
     */
    private String getValueForField(List<BookSearchRequest.SearchCriterion> criteria, String idx) {
        return criteria.stream()
                .filter(criterion -> criterion.getIdx().equalsIgnoreCase(idx))
                .map(BookSearchRequest.SearchCriterion::getSearchTerm)
                .findFirst()
                .orElse(null);
    }

}
