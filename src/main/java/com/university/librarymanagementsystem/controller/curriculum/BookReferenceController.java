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

import com.university.librarymanagementsystem.dto.curriculum.BookReferenceDTO;
import com.university.librarymanagementsystem.service.curriculum.BookReferenceService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/public/reference")
public class BookReferenceController {

    private BookReferenceService bookReferenceService;

    @PostMapping
    public ResponseEntity<Object> addBookReference(@RequestBody BookReferenceDTO bookRefDTO) {
        try {
            BookReferenceDTO savedBookReference = bookReferenceService.addBookReference(bookRefDTO);
            return new ResponseEntity<>(savedBookReference, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    // @GetMapping
    // public ResponseEntity<List<BookReferenceDTO>> getAllBookReference() {
    // List<BookReferenceDTO> bookReferences =
    // bookReferenceService.getAllBookReference();

    // return ResponseEntity.ok(bookReferences);
    // }

    // @GetMapping("/subject/{id}")
    // public ResponseEntity<List<BookReferenceDTO>>
    // getAllBookReferenceBySubject(@PathVariable("id") Integer subjectId) {
    // List<BookReferenceDTO> bookReferences =
    // bookReferenceService.getAllBookRefBySubject(subjectId);

    // return ResponseEntity.ok(bookReferences);
    // }

    // @PutMapping("{id}")
    // public ResponseEntity<BookReferenceDTO>
    // updateBookReference(@PathVariable("id") Integer bookRefId,
    // @RequestBody BookReferenceDTO updatedBookRef) {

    // try {
    // BookReferenceDTO bookRefDTO = bookReferenceService.updateBookRef(bookRefId,
    // updatedBookRef);

    // return ResponseEntity.ok(bookRefDTO);
    // } catch (Exception e) {
    // e.printStackTrace();
    // return null;
    // }
    // }

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
