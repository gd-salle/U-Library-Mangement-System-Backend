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
public class FineDto {

    private Long fineId;
    private Long loanId;
    private Long userId;
    private LocalDateTime fineDate;
    private Double fineAmount;
}
