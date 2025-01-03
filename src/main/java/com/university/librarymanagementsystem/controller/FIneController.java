package com.university.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.FineDto;
import com.university.librarymanagementsystem.entity.Fine;
import com.university.librarymanagementsystem.service.FineService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/adminuser/")
public class FineController {

    @Autowired
    private FineService fineService;

    @PostMapping("calculate")
    public ResponseEntity<List<Fine>> calculateFines() {
        List<Fine> fines = fineService.calculateAndSaveFines();
        return ResponseEntity.ok(fines);
    }

    @GetMapping("get-all-fines")
    public ResponseEntity<List<FineDto>> getAllFines() {
        return ResponseEntity.ok(fineService.getAllFines());
    }

}
