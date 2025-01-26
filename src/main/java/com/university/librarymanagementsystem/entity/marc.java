package com.university.librarymanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marc")
@Data
public class marc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "cat_source")
    private String catSource;

    @Column(name = "loc_call_number")
    private String locCallNumber;

    @Column(name = "ddc")
    private String ddc;

    @Column(name = "personal_name")
    private String personalName;

    @Column(name = "corporate_name")
    private String corporateName;

    @Column(name = "title")
    private String title;

    @Column(name = "edition")
    private String edition;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "physical_desc")
    private String physicalDesc;

    @Column(name = "content")
    private String content;

    @Column(name = "media")
    private String media;

    @Column(name = "gender")
    private String gender;

    @Column(name = "associated_language")
    private String associatedLanguage;

    @Column(name = "series")
    private String series;

    @Column(name = "biblio")
    private String biblio;

    @Column(name = "content_note")
    private String contentNote;

    @Column(name = "production_note")
    private String productionNote;

    @Column(name = "summary")
    private String summary;

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "funding_source")
    private String fundingSource;

    @Column(name = "tropical_term")
    private String tropicalTerm;

    @Column(name = "historical_data")
    private String historicalData;

    @Column(name = "other_authors")
    private String otherAuthors;
}
