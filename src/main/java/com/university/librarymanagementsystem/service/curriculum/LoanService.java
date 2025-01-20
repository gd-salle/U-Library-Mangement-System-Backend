package com.university.librarymanagementsystem.service.curriculum;

import java.util.List;

import com.university.librarymanagementsystem.dto.circulation.BorrowerDetailsDto;
import com.university.librarymanagementsystem.dto.circulation.LoanDto;
import com.university.librarymanagementsystem.entity.circulation.Loans;

public interface LoanService {
    List<LoanDto> getAllLoanDetails();

    BorrowerDetailsDto getBorrowerDetails(String libraryCardNumber);

    LoanDto saveLoan(LoanDto loanDto);

    List<LoanDto> getLoansDetails(Long loanId);

    LoanDto updateLoanStatus(Long loanId, LoanDto loanDto);

    List<Loans> getOverdueLoans();

    boolean isBookLoanedByBarcode(String barcode);

}
