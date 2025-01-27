package com.university.librarymanagementsystem.repository.curriculum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.curriculum.Program;
import com.university.librarymanagementsystem.entity.curriculum.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndProgram(String name, Program program);

    @Query("SELECT s FROM Subject s WHERE s.program.id = :programId")
    List<Subject> findAllSubjectsByProgram(@Param("programId") Integer programId);
}
