package com.university.librarymanagementsystem.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.BorrowerDetailsDto;
import com.university.librarymanagementsystem.dto.LoanDto;
import com.university.librarymanagementsystem.entity.Book;
import com.university.librarymanagementsystem.entity.Loans;
import com.university.librarymanagementsystem.entity.Users;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.LoanMapper;
import com.university.librarymanagementsystem.repository.BookRepository;
import com.university.librarymanagementsystem.repository.LoanRepository;
import com.university.librarymanagementsystem.repository.UserRepo;
import com.university.librarymanagementsystem.service.LoanService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<LoanDto> getAllLoanDetails() {
        List<Object[]> rawResults = loanRepository.findAllLoanDetails();
        return rawResults.stream().map(LoanMapper::toLoanDto).toList(); // Map raw data to LoanDto
    }

    @Override
    public BorrowerDetailsDto getBorrowerDetails(String libraryCardNumber) {
        List<Object[]> result = userRepo.findByLibraryCardNumberWithDepartment(libraryCardNumber);
        if (result.isEmpty()) {
            throw new RuntimeException("Borrower not found for the provided library card number.");
        }
        Object[] row = result.get(0);
        return new BorrowerDetailsDto((String) row[0], (String) row[1]);
    }

    @Override
    public LoanDto saveLoan(LoanDto loanDto) {
        // Find the book by accession number
        Book book = bookRepository.findByAccessionNo(loanDto.getAccessionNo())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        // Check if the book is already loaned out
        if ("Loaned Out".equals(book.getStatus())) {
            throw new IllegalStateException("Book is already loaned out");
        }

        // Find the user by borrower ID (library card number)
        Users user = userRepo.findByLibraryCardNumber(loanDto.getBorrower())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Update book status to Loaned Out
        book.setStatus("Loaned Out");
        bookRepository.save(book);

        // Map LoanDto to Loan entity
        Loans loan = new Loans();
        loan.setBook(book);
        loan.setUser(user); // Set the user reference
        loan.setBorrowDate(loanDto.getBorrowDate() != null ? loanDto.getBorrowDate() : LocalDateTime.now());
        loan.setDueDate(loanDto.getDueDate());
        loan.setReturnDate(loanDto.getReturnDate());
        loan.setStatus("Borrowed");

        // Save the loan record
        Loans savedLoan = loanRepository.save(loan);

        LoanDto responseDto = new LoanDto();
        responseDto.setLoanId(savedLoan.getLoanId());
        responseDto.setAccessionNo(book.getAccessionNo());
        responseDto.setTitle(book.getTitle());
        responseDto.setBorrowDate(savedLoan.getBorrowDate());
        responseDto.setDueDate(savedLoan.getDueDate());
        responseDto.setStatus(savedLoan.getStatus());

        return responseDto;
    }

    @Override
    public List<LoanDto> getLoansDetails(Long loanId) {
        List<Object[]> rawResults = loanRepository.findLoanDetailById(loanId);
        return rawResults.stream().map(LoanMapper::toLoanDto).toList();
    }

    @Override
    public LoanDto updateLoanStatus(Long loanId, LoanDto loanDto) {
        // Find the loan by loan ID
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        // Find the associated book by the loaned book's accession number
        Book book = loan.getBook();

        // Check if the loan status is "Returned", and update the return date and book
        // status
        if ("Returned".equals(loanDto.getStatus())) {
            // Update the return date to the current date
            loan.setReturnDate(LocalDateTime.now());

            // Update the book status to "Available"
            book.setStatus("Available");
            bookRepository.save(book);
        }

        // Update the loan status
        loan.setStatus(loanDto.getStatus());

        // Save the updated loan record
        Loans updatedLoan = loanRepository.save(loan);

        // Map the updated loan and book details to LoanDto
        LoanDto responseDto = new LoanDto();
        responseDto.setLoanId(updatedLoan.getLoanId());
        responseDto.setAccessionNo(book.getAccessionNo());
        responseDto.setTitle(book.getTitle());
        responseDto.setBorrowDate(updatedLoan.getBorrowDate());
        responseDto.setReturnDate(updatedLoan.getReturnDate());
        responseDto.setDueDate(updatedLoan.getDueDate());
        responseDto.setStatus(updatedLoan.getStatus());

        return responseDto;
    }

}
