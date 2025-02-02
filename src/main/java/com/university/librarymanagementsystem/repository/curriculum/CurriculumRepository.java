package com.university.librarymanagementsystem.repository.curriculum;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.librarymanagementsystem.entity.curriculum.Curriculum;

public interface CurriculumRepository extends JpaRepository<Curriculum, String> {

}
