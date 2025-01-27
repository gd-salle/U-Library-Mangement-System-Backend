package com.university.librarymanagementsystem.service.circulation;

import java.util.List;

import com.university.librarymanagementsystem.dto.circulation.BorrowerDetailsDto;
import com.university.librarymanagementsystem.dto.circulation.LoanDto;
import com.university.librarymanagementsystem.entity.circulation.Loans;

public interface LoanService {
    List<LoanDto> getAllLoanDetails();

    List<LoanDto> getAllLoanWithBorrowedStatus();

    BorrowerDetailsDto getBorrowerDetails(String libraryCardNumber);

    LoanDto saveLoan(LoanDto loanDto);

    List<LoanDto> getLoansDetails(Long loanId);

    LoanDto updateLoanStatus(Long loanId, LoanDto loanDto, String action);

    List<Loans> getOverdueLoans();

    boolean isBookLoanedByBarcode(String barcode);

}
