package com.university.librarymanagementsystem.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.catalog.BookWeedingStatus;

@Repository
public interface BookWeedingStatusRepository extends JpaRepository<BookWeedingStatus, Long> {

}
