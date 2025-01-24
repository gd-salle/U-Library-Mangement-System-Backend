package com.university.librarymanagementsystem.dto.circulation;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(pattern = "MM/dd/yyyy, HH:mm:ss")
    private LocalDateTime borrowDate;
    @JsonFormat(pattern = "MM/dd/yyyy, HH:mm:ss")
    private LocalDateTime returnDate;
    @JsonFormat(pattern = "MM/dd/yyyy, HH:mm:ss")
    private LocalDateTime dueDate;
    private String status;
}
