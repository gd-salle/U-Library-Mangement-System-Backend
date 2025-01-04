package com.university.librarymanagementsystem.repository.curriculum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.curriculum.BookReference;

@Repository
public interface BookReferenceRepository extends JpaRepository<BookReference, Integer> {

    @Query("SELECT b FROM BookReference b WHERE b.subject.id = :subjectId")
    List<BookReference> findAllBookReferenceBySubject(@Param("subjectId") Integer subjectId);
}