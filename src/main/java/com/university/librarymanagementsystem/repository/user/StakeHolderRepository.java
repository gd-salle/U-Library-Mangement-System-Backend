package com.university.librarymanagementsystem.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.user.StakeHolders;

@Repository
public interface StakeHolderRepository extends JpaRepository<StakeHolders, String> {

    @Query(value = "SELECT s.* FROM stakeholders s " +
            "INNER JOIN departments d ON s.dept_id = d.id " +
            "INNER JOIN programs c ON s.program_id = c.id " +
            "WHERE s.id = :studentId", nativeQuery = true)
    StakeHolders findStakeholderById(@Param("studentId") String studentId);

}
