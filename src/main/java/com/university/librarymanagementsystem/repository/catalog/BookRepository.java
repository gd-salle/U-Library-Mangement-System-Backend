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
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

        @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.name = :authorName")
        List<Book> findBooksByAuthorName(@Param("authorName") String authorName);

        @Query("SELECT b FROM Book b WHERE b.accessionNo LIKE ?1%")
        List<Book> findBooksByAccessionNo(String locationPrefix);

        Optional<Book> findByBarcode(String barcode);

        Optional<Book> findByAccessionNo(String accessionNo);

        boolean existsByTitleAndIsbn10AndIsbn13(String title, String isbn10, String isbn13);

        Optional<Book> findTopByTitleAndIsbn10AndIsbn13OrderByAccessionNoDesc(String title, String isbn10,
                        String isbn13);

        Optional<Book> findTopByAccessionNoStartingWithOrderByAccessionNoDesc(String prefix);

}
