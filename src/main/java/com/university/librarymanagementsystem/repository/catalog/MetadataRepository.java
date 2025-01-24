package com.university.librarymanagementsystem.repository.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.librarymanagementsystem.entity.catalog.Metadata;

public interface MetadataRepository extends JpaRepository<Metadata, Long> {

}
