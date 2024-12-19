package com.university.librarymanagementsystem.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

     private Long id;
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private Integer pageCount;
    private String categories;
    private String language;
    private String isbn10;
    private String isbn13;
    private String thumbnail;
    private String printType;
    private String status;
    private String barcode;
    private String callNumber;
    private Double purchasePrice;
    private String circulationType;
    private LocalDate dateAcquired;
    private String notes;
    private String sublocation;
    private String vendor;
    private String fundingSource;
    private String subjects;
    
}
