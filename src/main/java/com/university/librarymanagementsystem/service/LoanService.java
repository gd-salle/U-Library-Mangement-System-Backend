package com.university.librarymanagementsystem.service;

import java.util.List;

import com.university.librarymanagementsystem.dto.BorrowerDetailsDto;
import com.university.librarymanagementsystem.dto.LoanDto;
import com.university.librarymanagementsystem.entity.Loans;

public interface LoanService {
    List<LoanDto> getAllLoanDetails();

    BorrowerDetailsDto getBorrowerDetails(String libraryCardNumber);

    LoanDto saveLoan(LoanDto loanDto);

    List<LoanDto> getLoansDetails(Long loanId);

    LoanDto updateLoanStatus(Long loanId, LoanDto loanDto);

    List<Loans> getOverdueLoans();

    boolean isBookLoanedByBarcode(String barcode);

}
