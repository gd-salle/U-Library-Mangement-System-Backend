package com.university.librarymanagementsystem.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.catalog.CutterSanbornNumber;

@Repository
public interface CutterNumberRepository extends JpaRepository<CutterSanbornNumber, Long> {

}
