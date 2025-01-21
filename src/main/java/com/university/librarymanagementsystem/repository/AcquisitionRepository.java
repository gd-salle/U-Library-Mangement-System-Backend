package com.university.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.Acquisition;

@Repository
public interface AcquisitionRepository extends JpaRepository<Acquisition, Integer> {

    // this query is for fetching all records that status 0 or havent caloged yet
    @Query(value = "SELECT * FROM acquisition WHERE status = 0", nativeQuery = true)
    List<Acquisition> getPendingRecords();
}
