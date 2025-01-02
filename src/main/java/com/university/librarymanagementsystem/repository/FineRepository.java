package com.university.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.Fine;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {

}
