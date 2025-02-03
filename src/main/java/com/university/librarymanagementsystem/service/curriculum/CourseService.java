package com.university.librarymanagementsystem.service.curriculum;

import com.university.librarymanagementsystem.dto.curriculum.CourseDTO;

public interface CourseService {
    CourseDTO addCourse(CourseDTO courseDTO);

    // List<CourseDTO> addSubjects(List<CourseDTO> subjectDTOs);

    // CourseDTO getSubjectByID(Integer subjectID);

    // List<CourseDTO> getAllSubjects();

    // List<CourseDTO> getAllSubjectsByProgram(Integer programId);

    // CourseDTO updateSubject(Integer subjectID, CourseDTO updateSubject);
}
