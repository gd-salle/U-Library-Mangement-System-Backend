package com.university.librarymanagementsystem.controller.curriculum;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.curriculum.ProgramDTO;
import com.university.librarymanagementsystem.service.curriculum.ProgramService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/public/programs")
public class ProgramController {

    private ProgramService programService;

    // ADDING OF PROGRAMS
    @PostMapping
    public ResponseEntity<ProgramDTO> addProgram(@RequestBody ProgramDTO programDTO) {
        System.out.println("Received Department ID:" + programDTO.getDepartment_id());
        ProgramDTO savedProgram = programService.addProgram(programDTO);
        return new ResponseEntity<>(savedProgram, HttpStatus.CREATED);
    }

    // ADDING MULTIPLE PROGRAMS
    @PostMapping("/bulk")
    public ResponseEntity<List<ProgramDTO>> addPrograms(@RequestBody List<ProgramDTO> programDTOs) {
        List<ProgramDTO> savedPrograms = programService.addPrograms(programDTOs);
        return new ResponseEntity<>(savedPrograms, HttpStatus.CREATED);
    }

    // FETCHING PROGRAM BY ID
    @GetMapping("{id}")
    public ResponseEntity<ProgramDTO> getProgramByID(@PathVariable("id") Integer programID) {
        ProgramDTO programDTO = programService.getProgramByID(programID);

        return ResponseEntity.ok(programDTO);
    }

    // FETCHING ALL PROGRAMS
    @GetMapping
    public ResponseEntity<List<ProgramDTO>> getAllPrograms() {
        List<ProgramDTO> programs = programService.getAllPrograms();

        return ResponseEntity.ok(programs);
    }

    // FETCHING ALL PROGRAMS BY DEPARTMENT ID
    @GetMapping("/department/{id}")
    public ResponseEntity<List<ProgramDTO>> getAllProgramsByDepartment(@PathVariable("id") Integer departmentID) {
        List<ProgramDTO> programs = programService.getAllProgramsByDepartment(departmentID);

        return ResponseEntity.ok(programs);
    }

    // UPDATING PROGRAM
    @PutMapping("{id}")
    public ResponseEntity<ProgramDTO> updateProgram(@PathVariable("id") Integer programID,
            @RequestBody ProgramDTO updatedProgram) {
        ProgramDTO programDTO = programService.updateDepartment(programID, updatedProgram);

        return ResponseEntity.ok(programDTO);
    }
}
