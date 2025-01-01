package com.university.librarymanagementsystem.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {

    private Long loanId;
    private String accessionNo;
    private String barcode;
    private String title;
    private String callNumber;
    private String authorName;
    private String borrower;
    private String departmentName;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private LocalDateTime dueDate;
    private String status;
}
