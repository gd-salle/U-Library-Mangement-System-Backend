package com.university.librarymanagementsystem.service.curriculum;

import java.util.List;

import com.university.librarymanagementsystem.dto.curriculum.ProgramDTO;

public interface ProgramService {
    ProgramDTO addProgram(ProgramDTO programDTO);

    ProgramDTO getProgramByID(Integer programID);

    List<ProgramDTO> getAllPrograms();

    List<ProgramDTO> getAllProgramsByDepartment(Integer departmentID);

    ProgramDTO updateDepartment(Integer programID, ProgramDTO updatedProgram);
}