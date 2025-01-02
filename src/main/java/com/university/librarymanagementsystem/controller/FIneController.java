package com.university.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.FineDto;
import com.university.librarymanagementsystem.service.FineService;

@RestController
@RequestMapping("/adminuser/")
public class FIneController {

    @Autowired
    private FineService fineService;

    @GetMapping("all-fines")
    public ResponseEntity<List<FineDto>> getAllFines() {
        return ResponseEntity.ok(fineService.getAllFineDetails());
    }

}
