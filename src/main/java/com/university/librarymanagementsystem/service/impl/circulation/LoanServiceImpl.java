package com.university.librarymanagementsystem.service.impl.circulation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.circulation.BorrowerDetailsDto;
import com.university.librarymanagementsystem.dto.circulation.LoanDto;
import com.university.librarymanagementsystem.entity.catalog.Book;
import com.university.librarymanagementsystem.entity.circulation.Loans;
import com.university.librarymanagementsystem.entity.user.StakeHolders;
import com.university.librarymanagementsystem.entity.user.Users;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.circulation.LoanMapper;
import com.university.librarymanagementsystem.mapper.user.StakeHolderMapper;
import com.university.librarymanagementsystem.repository.catalog.BookRepository;
import com.university.librarymanagementsystem.repository.circulation.FineRepository;
import com.university.librarymanagementsystem.repository.circulation.LoanRepository;
import com.university.librarymanagementsystem.repository.user.StakeHolderRepository;
import com.university.librarymanagementsystem.repository.user.UserRepo;
import com.university.librarymanagementsystem.service.circulation.LoanService;
import com.university.librarymanagementsystem.service.impl.user.StakeHolderServiceImpl;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private StakeHolderRepository stakeHolderRepository;

    @Override
    public List<LoanDto> getAllLoanDetails() {
        List<Object[]> rawResults = loanRepository.findAllLoanDetails();
        return rawResults.stream().map(LoanMapper::toLoanDto).toList(); // Map raw data to LoanDto
    }

    @Override
    public List<LoanDto> getAllLoanWithBorrowedStatus() {
        List<Object[]> result = loanRepository.findAllBorrowedLoans();
        return result.stream().map(LoanMapper::toLoanDto).toList();
    }

    @Override
    public BorrowerDetailsDto getBorrowerDetails(String id) {
        Users user = userRepo.findBySchoolId(id);

        boolean isActivated = user != null;

        StakeHolders stakeHolder = stakeHolderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found: " + id));

        boolean hasCurrentBorrowedBook = loanRepository.findAll().stream()
                .anyMatch(loan -> loan.getUser().getSchoolId().equals(stakeHolder.getId()) &&
                        "borrowed".equalsIgnoreCase(loan.getStatus()));

        BorrowerDetailsDto borrowerDetailsDto = StakeHolderMapper.mapToBorrowerDetailsDto(stakeHolder);
        borrowerDetailsDto.setHasCurrentBorrowedBook(hasCurrentBorrowedBook);
        borrowerDetailsDto.setRegistered(isActivated);

        return borrowerDetailsDto;
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
        Users user = userRepo.findBySchoolId(loanDto.getBorrower());

        // Update book status to Loaned Out
        book.setStatus("Loaned Out");
        bookRepository.save(book);

        // Map LoanDto to Loan entity
        Loans loan = new Loans();
        loan.setBook(book);
        loan.setUser(user);

        // Set borrowDate to the current time in the Manila time zone (GMT+8)
        loan.setBorrowDate(loanDto.getBorrowDate() != null
                ? loanDto.getBorrowDate().atZone(ZoneId.of("Asia/Manila")).toLocalDateTime()
                : LocalDateTime.now(ZoneId.of("Asia/Manila")));

        // Ensure dueDate and returnDate are also adjusted to the Manila time zone if
        // necessary
        if (loanDto.getDueDate() != null) {
            loan.setDueDate(loanDto.getDueDate().atZone(ZoneId.of("Asia/Manila")).toLocalDateTime());
        }
        if (loanDto.getReturnDate() != null) {
            loan.setReturnDate(loanDto.getReturnDate().atZone(ZoneId.of("Asia/Manila")).toLocalDateTime());
        }

        loan.setStatus("Borrowed");

        // Save the loan record
        Loans savedLoan = loanRepository.save(loan);

        // Map savedLoan to LoanDto
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
    public LoanDto updateLoanStatus(Long loanId, LoanDto loanDto, String action) {
        // Find the loan by loan ID
        Loans loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        // Find the associated book by the loaned book's accession number
        Book book = loan.getBook();
        System.out.println("STATUS:" + action);
        // Handle "Returned" status updates
        if ("Returned".equals(loanDto.getStatus())) {
            // Update the return date to the current date if not provided
            loan.setReturnDate(loanDto.getReturnDate() != null ? loanDto.getReturnDate() : LocalDateTime.now());

            // Update the book status to "Available"
            book.setStatus("Available");
            bookRepository.save(book); // Save the updated book
        }
        // Handle "Renewed" status updates
        if ("Renewed".equals(action)) {
            // Update borrow date to the current date/time if not provided
            LocalDateTime newBorrowDate = loanDto.getBorrowDate() != null
                    ? loanDto.getBorrowDate().atZone(ZoneId.of("Asia/Manila")).toLocalDateTime()
                    : LocalDateTime.now(ZoneId.of("Asia/Manila"));

            loan.setBorrowDate(newBorrowDate);

            // Update the due date to one day after the new borrow date
            loan.setDueDate(newBorrowDate.plusDays(1));

            // Set the loan status to "Borrowed"
            loan.setStatus("Borrowed");

            // Update the book's status to "Loaned Out"
            book.setStatus("Loaned Out");
            bookRepository.save(book); // Save the updated book
        }

        // Update the loan status in all cases
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

        System.out.println("Updating loan status: {}" + loanDto.getStatus());
        System.out.println("New borrow date: {}" + updatedLoan.getBorrowDate());
        return responseDto;
    }

    @Override
    public List<Loans> getOverdueLoans() {
        return loanRepository.findOverdueLoans(LocalDateTime.now());
    }

    @Override
    public boolean isBookLoanedByAccessionNo(String accessionNo) {

        List<Loans> activeLoans = loanRepository.findByBookAccessionNoAndStatus(accessionNo, "Borrowed");
        return !activeLoans.isEmpty(); // If there are active loans, the book is loaned out
    }

}
