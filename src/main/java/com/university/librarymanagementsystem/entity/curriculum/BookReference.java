package com.university.librarymanagementsystem.entity.curriculum;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.university.librarymanagementsystem.entity.Book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "book_reference")
@Data
public class BookReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "books_id", nullable = false)
    // @JsonIgnore
    // @ToString.Exclude
    @Column(name = "book_name", nullable = false)
    private String book_name;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "urlPath", nullable = false)
    private String urlPath;
}
