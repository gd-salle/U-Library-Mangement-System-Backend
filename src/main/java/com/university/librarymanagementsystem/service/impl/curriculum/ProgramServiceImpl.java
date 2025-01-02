package com.university.librarymanagementsystem.service.impl.curriculum;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.curriculum.ProgramDTO;
import com.university.librarymanagementsystem.entity.curriculum.Department;
import com.university.librarymanagementsystem.entity.curriculum.Program;
import com.university.librarymanagementsystem.exception.DuplicateEntryException;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.curriculum.ProgramMapper;
import com.university.librarymanagementsystem.repository.curriculum.DepartmentRepository;
import com.university.librarymanagementsystem.repository.curriculum.ProgramRepository;
import com.university.librarymanagementsystem.service.curriculum.ProgramService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private ProgramRepository programRepository;
    private DepartmentRepository departmentRepository;

    @Override
    public ProgramDTO addProgram(ProgramDTO programDTO) {
        System.out.println("DEPARTMENT ID:" + programDTO.getDepartment_id());
        Department department = departmentRepository.findById(programDTO.getDepartment_id())
                .orElseThrow(() -> new RuntimeException("Department not Found!"));

        Program program = ProgramMapper.mapToProgram(programDTO);

        boolean isExisting = programRepository.existsByName(programDTO.getName());

        if (isExisting) {
            throw new DuplicateEntryException("A program with the same name is already existed!");
        }

        program.setDepartment(department);

        Program savedProgram = programRepository.save(program);

        return ProgramMapper.mapToProgramDTO(savedProgram);
    }

    @Override
    public ProgramDTO getProgramByID(Integer programID) {
        Program program = programRepository.findById(programID)
                .orElseThrow(() -> new ResourceNotFoundException("not existing" + programID));

        return ProgramMapper.mapToProgramDTO(program);
    }

    @Override
    public List<ProgramDTO> getAllPrograms() {
        List<Program> programs = programRepository.findAll();

        return programs.stream().map((program) -> ProgramMapper.mapToProgramDTO(program))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProgramDTO> getAllProgramsByDepartment(Integer departmentID) {
        List<Program> programs = programRepository.findByDepartmentId(departmentID);

        return programs.stream().map((program) -> ProgramMapper.mapToProgramDTO(program))
                .collect(Collectors.toList());
    }

    @Override
    public ProgramDTO updateDepartment(Integer programID, ProgramDTO updatedProgram) {
        Program program = programRepository.findById(programID)
                .orElseThrow(() -> new ResourceNotFoundException("Program not existing with given ID" + programID));

        Department department = departmentRepository.findById(updatedProgram.getDepartment_id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not existing with the given ID" + updatedProgram.getDepartment_id()));
        program.setName(updatedProgram.getName());
        program.setDepartment(department);
        program.setStatus(updatedProgram.getStatus());

        Program updatedEntity = programRepository.save(program);

        return ProgramMapper.mapToProgramDTO(updatedEntity);
    }

}