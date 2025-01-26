package com.university.librarymanagementsystem.controller.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.catalog.BookWeedingStatusDTO;
import com.university.librarymanagementsystem.service.catalog.BookWeedingStatusService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin/book-weeding")
public class BookWeedingStatus {

    @Autowired
    private BookWeedingStatusService bookWeedingStatusService;

    @GetMapping
    public ResponseEntity<List<BookWeedingStatusDTO>> getAllBookWeedingStatus() {
        return ResponseEntity.ok(bookWeedingStatusService.getAllBookWeedingStatuses());
    }

}
