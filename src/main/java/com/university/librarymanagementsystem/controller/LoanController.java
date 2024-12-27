package com.university.librarymanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.LoanDto;
import com.university.librarymanagementsystem.service.LoanService;

@RestController
@RequestMapping("/admin/")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("all-loans")
    public ResponseEntity<List<LoanDto>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoanDetails());
    }

}
