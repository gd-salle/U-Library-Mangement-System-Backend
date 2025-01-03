package com.university.librarymanagementsystem.mapper.curriculum;

import org.springframework.stereotype.Component;

import com.university.librarymanagementsystem.dto.curriculum.SubjectDTO;
import com.university.librarymanagementsystem.entity.curriculum.Program;
import com.university.librarymanagementsystem.entity.curriculum.Subject;

@Component
public class SubjectMapper {

    public static SubjectDTO mapToSubjectDTO(Subject subject) {
        return new SubjectDTO(
                subject.getId(),
                subject.getProgram().getId(),
                subject.getProgram().getName(),
                subject.getProgram().getDepartment().getId(),
                subject.getProgram().getDepartment().getName(),
                subject.getName(),
                subject.getYear(),
                subject.getStatus());

    }

    public static Subject maptoSubject(SubjectDTO subjectDTO) {
        Program program = new Program();
        program.setId(subjectDTO.getProgram_id());

        return new Subject(
                subjectDTO.getId(),
                program,
                subjectDTO.getDepartment_id(),
                subjectDTO.getSubject_name(),
                subjectDTO.getYear(),
                subjectDTO.getStatus());

    }
}