package com.university.librarymanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.dto.LoanDto;
import com.university.librarymanagementsystem.entity.Loans;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {

    @Query(value = "SELECT " +
            "l.loan_id AS loanId, " +
            "b.title AS title, " +
            "b.call_number AS callNumber, " +
            "a.name AS authorName, " +
            "CONCAT(s.first_name, ' ', COALESCE(s.middle_name, ''), ' ', s.last_name, COALESCE(CONCAT(' ', s.suffix), '')) AS borrowerFullName, "
            +
            "d.name AS departmentName, " +
            "s.id AS borrowerId, " +
            "l.borrow_date AS borrowDate, " +
            "l.return_date AS returnDate, " +
            "l.status AS status " +
            "FROM loan l " +
            "JOIN books b ON l.book_id = b.id " +
            "JOIN book_author ba ON b.id = ba.book_id " +
            "JOIN authors a ON ba.author_id = a.id " +
            "JOIN users u ON l.user_id = u.user_id " +
            "JOIN stakeholders s ON u.school_id = s.id " +
            "JOIN department d ON s.department = d.id", nativeQuery = true)
    List<Object[]> findAllLoanDetails();

}
