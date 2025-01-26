package com.university.librarymanagementsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.AcquisitionDTO;
import com.university.librarymanagementsystem.service.AcquisitionService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@RestController
@RequestMapping("/adminuser")
public class AcquisitionController {

    private AcquisitionService acquisitionService;

    @PostMapping("/add-record")
    public ResponseEntity<List<AcquisitionDTO>> addRecords(@RequestBody List<AcquisitionDTO> acquisitionDTOs) {
        List<AcquisitionDTO> savedRecords = acquisitionService.addRecords(acquisitionDTOs);

        return new ResponseEntity<>(savedRecords, HttpStatus.CREATED);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<AcquisitionDTO>> getPendingCatalogRecords() {
        List<AcquisitionDTO> pendingRecords = acquisitionService.getPendingCatalogRecords();

        return ResponseEntity.ok(pendingRecords);
    }

    @PostMapping("/update-status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Integer id) {
        boolean success = acquisitionService.updateStatus(id);

        if (success) {
            return new ResponseEntity<>("Status updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update status.", HttpStatus.BAD_REQUEST);
        }
    }
}
