package com.university.librarymanagementsystem.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.catalog.DdcClassification;

@Repository
public interface DdcRepository extends JpaRepository<DdcClassification, Long> {

    @Query("SELECT d FROM DdcClassification d WHERE LOWER(d.description) LIKE LOWER(CONCAT('%', :category, '%'))")
    DdcClassification findByDescriptionLikeIgnoreCase(@Param("category") String category);
}