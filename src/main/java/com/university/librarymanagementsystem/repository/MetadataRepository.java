package com.university.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.librarymanagementsystem.entity.Metadata;

public interface MetadataRepository extends JpaRepository<Metadata, Long> {

}
