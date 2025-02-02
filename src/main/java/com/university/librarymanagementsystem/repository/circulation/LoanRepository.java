package com.university.librarymanagementsystem.repository.circulation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.circulation.Loans;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {

        @Query(value = "SELECT " +
                        "l.loan_id AS loanId, " +
                        "b.accession_no AS accessionNo, " +
                        "b.title AS title, " +
                        "b.call_number AS callNumber, " +
                        "a.name AS authorName, " +
                        "u.library_card_number AS borrower, "
                        +
                        "d.dept_name AS departmentName, " +
                        "l.borrow_date AS borrowDate, " +
                        "l.return_date AS returnDate, " +
                        "l.due_date AS dueDate, " +
                        "l.status AS status " +
                        "FROM loan l " +
                        "JOIN books b ON l.book_id = b.id " +
                        "JOIN book_author ba ON b.id = ba.book_id " +
                        "JOIN authors a ON ba.author_id = a.id " +
                        "JOIN users u ON l.user_id = u.user_id " +
                        "JOIN stakeholders s ON u.school_id = s.id " +
                        "JOIN departments d ON s.department = d.dept_id", nativeQuery = true)
        List<Object[]> findAllLoanDetails();

        @Query(value = "SELECT " +
                        "l.loan_id AS loanId, " +
                        "b.accession_no AS accessionNo, " +
                        "b.title AS title, " +
                        "b.call_number AS callNumber, " +
                        "a.name AS authorName, " +
                        "u.library_card_number AS borrower, " +
                        "d.dept_name AS departmentName, " +
                        "l.borrow_date AS borrowDate, " +
                        "l.return_date AS returnDate, " +
                        "l.due_date AS dueDate, " +
                        "l.status AS status " +
                        "FROM loan l " +
                        "JOIN books b ON l.book_id = b.id " +
                        "JOIN book_author ba ON b.id = ba.book_id " +
                        "JOIN authors a ON ba.author_id = a.id " +
                        "JOIN users u ON l.user_id = u.user_id " +
                        "JOIN stakeholders s ON u.school_id = s.id " +
                        "JOIN departments d ON s.department = d.dept_id " +
                        "WHERE l.status = 'Borrowed'", nativeQuery = true)
        List<Object[]> findAllBorrowedLoans();

        @Query(value = "SELECT " +
                        "l.loan_id AS loanId, " +
                        "b.accession_no AS accessionNo, " +
                        "b.title AS title, " +
                        "b.call_number AS callNumber, " +
                        "a.name AS authorName, " +
                        "u.library_card_number AS borrower, " +
                        "d.dept_name AS departmentName, " +
                        "l.borrow_date AS borrowDate, " +
                        "l.return_date AS returnDate, " +
                        "l.due_date AS dueDate, " +
                        "l.status AS status " +
                        "FROM loan l " +
                        "JOIN books b ON l.book_id = b.id " +
                        "JOIN book_author ba ON b.id = ba.book_id " +
                        "JOIN authors a ON ba.author_id = a.id " +
                        "JOIN users u ON l.user_id = u.user_id " +
                        "JOIN stakeholders s ON u.school_id = s.id " +
                        "JOIN departments d ON s.department = d.dept_id " +
                        "WHERE l.loan_id = :loanId", nativeQuery = true)
        List<Object[]> findLoanDetailById(Long loanId);

        @Query(value = "SELECT * FROM loan l WHERE l.due_date < :currentDate", nativeQuery = true)
        List<Loans> findOverdueLoans(@Param("currentDate") LocalDateTime currentDate);

        List<Loans> findByBookAccessionNoAndStatus(String accessionNo, String status);

        @Query(value = "SELECT l FROM loan l WHERE l.return_date IS NULL AND l.due_date < :currentDate", nativeQuery = true)
        List<Loans> findOverdueLoansList(LocalDateTime currentDate);

        @Query("SELECT COUNT(l) FROM Loans l WHERE l.book.id = :bookId AND l.borrowDate >= :startDate")
        long countLoansByBookIdWithinLastYear(@Param("bookId") Long bookId,
                        @Param("startDate") LocalDateTime startDate);

}