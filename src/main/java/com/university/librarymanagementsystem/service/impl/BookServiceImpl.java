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

        String keyword = getValueForField(request.getCriteria(), "q");
        String title = getValueForField(request.getCriteria(), "intitle");
        String authorName = getValueForField(request.getCriteria(), "inauthor");
        String publisher = getValueForField(request.getCriteria(), "inpublisher");
        String isbn = getValueForField(request.getCriteria(), "inisbn");
        String subjects = getValueForField(request.getCriteria(), "insubjects");

        // Extract selected options (itemType, sections, and collection)
        List<String> itemType = request.getItemType();
        List<String> sections = request.getSections();
        List<String> collection = request.getCollection();

        // Call repository method with the extracted and parsed parameters
        List<Book> books = bookRepository.advancedSearchBooks(
                keyword,
                title,
                authorName,
                publisher,
                isbn,
                subjects,
                collection,
                sections,
                itemType,
                request.getLanguage(),
                startDate,
                endDate,
                request.getIsAvailableOnly(),
                request.getIndividualLibrary(),
                request.getSortOrder());

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
     * @param idx      The index (field identifier) to look for, e.g., 'kw' for
     *                 keyword or 'ti' for title.
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
