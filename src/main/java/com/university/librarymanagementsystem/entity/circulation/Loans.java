package com.university.librarymanagementsystem.entity.circulation;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.university.librarymanagementsystem.entity.catalog.Book;
import com.university.librarymanagementsystem.entity.user.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "loan")
@Data
public class Loans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id") // Ensure this matches the column name in the database
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;
    @JsonFormat(pattern = "MM/dd/yyyy, HH:mm:ss")
    private LocalDateTime borrowDate;
    @JsonFormat(pattern = "MM/dd/yyyy, HH:mm:ss")
    private LocalDateTime returnDate;
    @JsonFormat(pattern = "MM/dd/yyyy, HH:mm:ss")
    private LocalDateTime dueDate;
    private String status;
}
