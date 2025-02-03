package com.university.librarymanagementsystem.repository.curriculum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.curriculum.Program;
import com.university.librarymanagementsystem.entity.curriculum.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // boolean existsByName(String name);

    // boolean existsByNameAndProgram(String name, Program program);

    // @Query("SELECT s FROM Course s WHERE s.program.id = :programId")
    // List<Course> findAllSubjectsByProgram(@Param("programId") Integer programId);
}
