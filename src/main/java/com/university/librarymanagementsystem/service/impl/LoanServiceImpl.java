package com.university.librarymanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.LoanDto;
import com.university.librarymanagementsystem.mapper.LoanMapper;
import com.university.librarymanagementsystem.repository.LoanRepository;
import com.university.librarymanagementsystem.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<LoanDto> getAllLoanDetails() {
        List<Object[]> rawResults = loanRepository.findAllLoanDetails();
        return rawResults.stream().map(LoanMapper::toLoanDto).toList(); // Map raw data to LoanDto
    }
}
