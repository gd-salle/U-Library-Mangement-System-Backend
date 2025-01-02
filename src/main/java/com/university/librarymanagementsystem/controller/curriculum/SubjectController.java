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

import com.university.librarymanagementsystem.dto.curriculum.SubjectDTO;
import com.university.librarymanagementsystem.exception.DuplicateEntryException;
import com.university.librarymanagementsystem.service.curriculum.SubjectService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/public/subjects")
public class SubjectController {

    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Object> addSubject(@RequestBody SubjectDTO subjectDTO) {
        try {
            System.out.println("Received Program ID: " + subjectDTO.getProgram_id());
            SubjectDTO savedSubject = subjectService.addSubject(subjectDTO);
            return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
        } catch (DuplicateEntryException e) {
            // Return a custom error response for duplicate subjects
            return ResponseEntity.badRequest().body(new ErrorResponse("Duplicate subject: " + e.getMessage()));
        } catch (Exception e) {
            // Handle any other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectDTO> getSubjectByID(@PathVariable("id") Integer subjectID) {
        SubjectDTO subjectDTO = subjectService.getSubjectByID(subjectID);

        return ResponseEntity.ok(subjectDTO);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllPrograms() {
        List<SubjectDTO> subjects = subjectService.getAllSubjects();

        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/program/{id}")
    public ResponseEntity<List<SubjectDTO>> getAllSubjectsByProgram(@PathVariable("id") Integer programId) {
        List<SubjectDTO> subjects = subjectService.getAllSubjectsByProgram(programId);

        return ResponseEntity.ok(subjects);
    }

    @PutMapping("{id}")
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable("id") Integer subjectID,
            @RequestBody SubjectDTO updatedSubject) {
        SubjectDTO subjectDTO = subjectService.updateSubject(subjectID, updatedSubject);

        return ResponseEntity.ok(subjectDTO);
    }

    static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
