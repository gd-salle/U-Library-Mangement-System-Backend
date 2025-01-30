package com.university.librarymanagementsystem.service.curriculum;

import java.util.List;

import com.university.librarymanagementsystem.dto.curriculum.SubjectDTO;

public interface SubjectService {
    SubjectDTO addSubject(SubjectDTO subjectDTO);

    List<SubjectDTO> addSubjects(List<SubjectDTO> subjectDTOs);

    SubjectDTO getSubjectByID(Integer subjectID);

    List<SubjectDTO> getAllSubjects();

    List<SubjectDTO> getAllSubjectsByProgram(Integer programId);

    SubjectDTO updateSubject(Integer subjectID, SubjectDTO updateSubject);
}
