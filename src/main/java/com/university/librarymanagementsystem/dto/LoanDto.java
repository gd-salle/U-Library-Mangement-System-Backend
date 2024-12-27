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
    private String title;
    private String callNumber;
    private String authorName;
    private String borrowerFullName;
    private String departmentName;
    private String borrowerId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private String status;
}
