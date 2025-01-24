package com.university.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class marcDTO {
    private int id;
    private String isbn;
    private String catSource;
    private String locCallNumber;
    private String ddc;
    private String personalName;
    private String corporateName;
    private String title;
    private String edition;
    private String publisher;
    private String physicalDesc;
    private String content;
    private String media;
    private String gender;
    private String associatedLanguage;
    private String series;
    private String biblio;
    private String contentNote;
    private String productionNote;
    private String summary;
    private String targetAudience;
    private String fundingSource;
    private String tropicalTerm;
    private String historicalData;
    private String otherAuthors;
}
