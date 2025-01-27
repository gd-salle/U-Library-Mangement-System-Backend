package com.university.librarymanagementsystem.repository.curriculum;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.curriculum.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE d.name = :name")
    boolean existsByName(@Param("name") String name);

    @Query(value = "SELECT * FROM department WHERE name = :departmentName", nativeQuery = true)
    Optional<Department> findByName(@Param("departmentName") String departmentName);
}