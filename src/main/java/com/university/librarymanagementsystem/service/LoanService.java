package com.university.librarymanagementsystem.service;

import java.util.List;

import com.university.librarymanagementsystem.dto.LoanDto;

public interface LoanService {
    List<LoanDto> getAllLoanDetails();
}
