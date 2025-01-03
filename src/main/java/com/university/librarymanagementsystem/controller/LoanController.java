package com.university.librarymanagementsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.BorrowerDetailsDto;
import com.university.librarymanagementsystem.dto.LoanDto;
import com.university.librarymanagementsystem.dto.OverdueLoanDTO;
import com.university.librarymanagementsystem.entity.Loans;
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

    @GetMapping("borrower-details/{cardNum}")
    public BorrowerDetailsDto getBorrowerDetails(@PathVariable("cardNum") String libraryCardNumber) {
        return loanService.getBorrowerDetails(libraryCardNumber);
    }

    @PostMapping("save-loan")
    public ResponseEntity<LoanDto> saveLoan(@RequestBody LoanDto loanDto) {
        return ResponseEntity.ok(loanService.saveLoan(loanDto));
    }

    @GetMapping("loans/barcode/{id}")
    public ResponseEntity<List<LoanDto>> getLoanDetailById(@PathVariable("id") Long param) {
        return ResponseEntity.ok(loanService.getLoansDetails(param));
    }

    @PutMapping("update/{loanId}")
    public ResponseEntity<LoanDto> updateLoan(@PathVariable Long loanId, @RequestBody LoanDto loanDto) {
        LoanDto updatedLoan = loanService.updateLoanStatus(loanId, loanDto);
        return ResponseEntity.ok(updatedLoan);
    }

    @GetMapping("check-book-loan-status/barcode/{barcode}")
    public ResponseEntity<Map<String, Boolean>> checkBookLoanStatus(@PathVariable String barcode) {
        boolean isLoaned = loanService.isBookLoanedByBarcode(barcode);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isLoaned", isLoaned);
        return ResponseEntity.ok(response);
    }

    @GetMapping("overdue")
    public List<Loans> getOverdueLoans() {
        return loanService.getOverdueLoans();
    }

}
