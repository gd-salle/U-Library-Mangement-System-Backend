package com.university.librarymanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.university.librarymanagementsystem.entity.StakeHolders;

public interface StakeHolderRepository extends JpaRepository<StakeHolders, String> {

    @Query("SELECT s FROM StakeHolders s INNER JOIN s.department d INNER JOIN s.course c WHERE s.id = :studentId")
    StakeHolders findStakeholderById(@Param("studentId") String studentId);

}
