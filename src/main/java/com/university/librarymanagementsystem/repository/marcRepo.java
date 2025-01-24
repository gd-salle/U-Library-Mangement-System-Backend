package com.university.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.marc;

@Repository
public interface marcRepo extends JpaRepository<marc, Integer> {
    @Query(value = "SELECT * FROM marc WHERE isbn = :isbn", nativeQuery = true)
    marc findByISBN(String isbn);
}
