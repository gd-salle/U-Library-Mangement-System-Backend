package com.university.librarymanagementsystem.service.impl.curriculum;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.curriculum.SubjectDTO;
import com.university.librarymanagementsystem.entity.curriculum.Program;
import com.university.librarymanagementsystem.entity.curriculum.Subject;
import com.university.librarymanagementsystem.exception.DuplicateEntryException;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.curriculum.SubjectMapper;
import com.university.librarymanagementsystem.repository.curriculum.ProgramRepository;
import com.university.librarymanagementsystem.repository.curriculum.SubjectRepository;
import com.university.librarymanagementsystem.service.curriculum.SubjectService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;
    private ProgramRepository programRepository;

    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {

        Program program = programRepository.findById(subjectDTO.getProgram_id())
                .orElseThrow(() -> new ResourceNotFoundException("Program not found!"));

        boolean isExisting = subjectRepository.existsByName(subjectDTO.getSubject_name());

        if (isExisting) {
            throw new DuplicateEntryException("A subject with the same name and program already exists.");
        }

        Subject subject = SubjectMapper.maptoSubject(subjectDTO);
        subject.setProgram(program);
        subject.setDepartment_id(program.getDepartment().getId());
        Subject savedSubject = subjectRepository.save(subject);

        System.out.println("SAVED:" + savedSubject);
        return SubjectMapper.mapToSubjectDTO((savedSubject));
    }

    @Override
    public List<SubjectDTO> addSubjects(List<SubjectDTO> subjectDTOs) {
        List<Subject> subjects = subjectDTOs.stream()
                .map(subjectDTO -> {
                    Program program = programRepository.findByName(subjectDTO.getProgram_name())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Program with ID " + subjectDTO.getProgram_id() + " not found!"));
                    Subject subject = SubjectMapper.maptoSubject(subjectDTO);
                    subject.setProgram(program);
                    subject.setDepartment_id(program.getDepartment().getId());
                    return subject;
                })
                .filter(subject -> !subjectRepository.existsByNameAndProgram(subject.getName(), subject.getProgram()))
                .collect(Collectors.toList());

        if (subjects.isEmpty()) {
            throw new DuplicateEntryException("All provided subjects already exist.");
        }

        List<Subject> savedSubjects = subjectRepository.saveAll(subjects);

        return savedSubjects.stream()
                .map(SubjectMapper::mapToSubjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubjectByID(Integer subjectID) {
        Subject subject = subjectRepository.findById(subjectID)
                .orElseThrow(() -> new ResourceNotFoundException("ID not existing:" + subjectID));

        return SubjectMapper.mapToSubjectDTO(subject);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();

        return subjects.stream().map((subject) -> SubjectMapper.mapToSubjectDTO(subject)).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDTO> getAllSubjectsByProgram(Integer departmentId) {
        List<Subject> subjects = subjectRepository.findAllSubjectsByProgram(departmentId);

        return subjects.stream().map((subject) -> SubjectMapper.mapToSubjectDTO(subject))
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDTO updateSubject(Integer subjectID, SubjectDTO updatedSubject) {
        Subject subject = subjectRepository.findById(subjectID)
                .orElseThrow(() -> new ResourceNotFoundException("Subject doesnt exist with ID" + subjectID));

        Program program = programRepository.findById(updatedSubject.getProgram_id()).orElseThrow(
                () -> new ResourceNotFoundException("Program doesnt exist with ID" + updatedSubject.getProgram_id()));

        // Only check for duplicate if the subject name is changing
        if (!subject.getName().equals(updatedSubject.getSubject_name())) {
            boolean isExisting = subjectRepository.existsByName(updatedSubject.getSubject_name());

            if (isExisting) {
                throw new DuplicateEntryException("A subject with the same name and program already exists.");
            }
        }

        subject.setName(updatedSubject.getSubject_name());
        subject.setProgram(program);
        subject.setDepartment_id(program.getDepartment().getId());
        subject.setYear(updatedSubject.getYear());

        Subject updatedEntity = subjectRepository.save(subject);

        System.out.println("Updated Entity:" + updatedEntity);

        return SubjectMapper.mapToSubjectDTO(updatedEntity);
    }

}
