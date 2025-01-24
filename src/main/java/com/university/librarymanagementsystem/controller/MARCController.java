package com.university.librarymanagementsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.marcDTO;
import com.university.librarymanagementsystem.service.marcService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/adminuser")
public class MARCController {
    private marcService marcService;

    @PostMapping("/add-marc-record")
    public ResponseEntity<Object> addMARCData(@RequestBody marcDTO marcDTO) {
        try {

            marcDTO savedMARCData = marcService.addMARCData(marcDTO);

            return new ResponseEntity<>(savedMARCData, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/marc/data/{isbn}")
    public ResponseEntity<Object> getMARCbyISBN(@PathVariable("isbn") String isbn) {
        try {
            marcDTO marcDTO = marcService.getMARCDataByISBN(isbn);
            return new ResponseEntity<>(marcDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Record not found: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
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
