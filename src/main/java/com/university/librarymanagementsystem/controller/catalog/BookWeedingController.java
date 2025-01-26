package com.university.librarymanagementsystem.controller.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.service.catalog.BookWeedingService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/admin/book-weeding")
public class BookWeedingController {

    @Autowired
    private BookWeedingService bookWeedingService;

    @PostMapping("/test")
    public ResponseEntity<String> triggerManualWeeding() {
        bookWeedingService.manualWeedingFlagging();
        return ResponseEntity.ok("Manual weeding process has been started.");
    }

}
