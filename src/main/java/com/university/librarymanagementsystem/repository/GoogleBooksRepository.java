package com.university.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.Book;

@Repository
public interface GoogleBooksRepository extends JpaRepository<Book, Long> {

}
