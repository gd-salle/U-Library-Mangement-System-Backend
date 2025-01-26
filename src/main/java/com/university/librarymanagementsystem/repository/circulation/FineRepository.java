package com.university.librarymanagementsystem.repository.circulation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.circulation.Fine;
import com.university.librarymanagementsystem.entity.circulation.Loans;

@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {

    boolean existsByLoan_LoanId(Long loanId);

    @Query(value = "SELECT " +
            "f.fine_id AS fineId, " +
            "f.loan_id AS loanId, " +
            "f.user_id AS userId, " +
            "s.id AS stakeholderId, " +
            "s.first_name AS firstName, " +
            "s.last_name AS lastName, " +
            "f.borrow_date AS borrowDate, " +
            "f.due_date AS dueDate, " +
            "f.return_date AS returnDate, " +
            "f.fine_amount AS fineAmount, " +
            "f.paid AS paid " +
            "FROM fine f " +
            "JOIN users u ON f.user_id = u.user_id " +
            "JOIN stakeholders s ON u.school_id = s.id", nativeQuery = true)
    List<Object[]> findAllFineDetails();
}