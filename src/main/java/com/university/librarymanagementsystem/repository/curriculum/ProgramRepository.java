package com.university.librarymanagementsystem.repository.curriculum;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.curriculum.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Program p WHERE p.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query("SELECT p FROM Program p WHERE p.department.id = :departmentId")
    List<Program> findByDepartmentId(@Param("departmentId") Integer departmentId);

    @Query(value = "SELECT * FROM program WHERE name = :programName", nativeQuery = true)
    Optional<Program> findByName(@Param("programName") String programName);
}
