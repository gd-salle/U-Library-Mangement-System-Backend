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
}
