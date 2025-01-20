package com.university.librarymanagementsystem.repository.catalog;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.catalog.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

        @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :authorName")
        List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

        @Query("SELECT b FROM Book b WHERE b.accessionNo LIKE ?1%")
        List<Book> findBooksByAccessionNo(String locationPrefix);

        Optional<Book> findByBarcode(String barcode);

        Optional<Book> findByAccessionNo(String accessionNo);

        // Custom query for advanced search
        @Query("SELECT DISTINCT b FROM Book b " +
                        "LEFT JOIN b.authors a " +
                        "WHERE " +
                        // Keyword search across multiple fields
                        "(:keyword IS NULL OR " +
                        " b.title LIKE CONCAT('%', :keyword, '%') OR " +
                        " b.description LIKE CONCAT('%', :keyword, '%') OR " +
                        " a.name LIKE CONCAT('%', :keyword, '%') OR " +
                        " b.categories LIKE CONCAT('%', :keyword, '%') OR " +
                        " b.subjects LIKE CONCAT('%', :keyword, '%') OR " +
                        " b.publisher LIKE CONCAT('%', :keyword, '%')) " +

                        // Title, Author, Publisher, ISBN filtering
                        "AND (:title IS NULL OR b.title LIKE CONCAT('%', :title, '%')) " +
                        "AND (:authorName IS NULL OR a.name LIKE CONCAT('%', :authorName, '%')) " +
                        "AND (:publisher IS NULL OR b.publisher LIKE CONCAT('%', :publisher, '%')) " +
                        "AND (:isbn10 IS NULL OR b.isbn10 LIKE CONCAT('%', :isbn10, '%')) " +
                        "AND (:isbn13 IS NULL OR b.isbn13 LIKE CONCAT('%', :isbn13, '%')) " +

                        // Language filtering
                        "AND (:language IS NULL OR b.language = :language) " +

                        // Date range filtering
                        "AND (:startDate IS NULL OR b.dateAcquired >= :startDate) " +
                        "AND (:endDate IS NULL OR b.dateAcquired <= :endDate) " +

                        // Availability filtering
                        "AND (:isAvailableOnly IS NULL OR :isAvailableOnly = false OR b.status = 'Available') " +

                        // Individual library filtering
                        "AND (:individualLibrary IS NULL OR b.location = :individualLibrary) " +

                        // Item type, Sections, Collection filtering
                        "AND (:itemType IS NULL OR b.printType IN (:itemType)) " +
                        "AND (:sections IS NULL OR b.section IN (:sections)) " +
                        "AND (:collection IS NULL OR b.categories IN (:collection)) " +

                        // Sorting logic
                        "ORDER BY " +
                        "CASE WHEN :sortOrder = 'Title A-Z' THEN b.title END ASC, " +
                        "CASE WHEN :sortOrder = 'Title Z-A' THEN b.title END DESC, " +
                        "CASE WHEN :sortOrder = 'Acquisition date: newest to oldest' THEN b.dateAcquired END DESC, " +
                        "CASE WHEN :sortOrder = 'Acquisition date: oldest to newest' THEN b.dateAcquired END ASC")
        List<Book> advancedSearchBooks(
                        @Param("keyword") String keyword,
                        @Param("title") String title,
                        @Param("authorName") String authorName,
                        @Param("publisher") String publisher,
                        @Param("isbn10") String isbn10,
                        @Param("isbn13") String isbn13,
                        @Param("collection") List<String> collection,
                        @Param("sections") List<String> sections,
                        @Param("itemType") List<String> itemType,
                        @Param("language") String language,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        @Param("isAvailableOnly") Boolean isAvailableOnly,
                        @Param("individualLibrary") String individualLibrary,
                        @Param("sortOrder") String sortOrder);

}
