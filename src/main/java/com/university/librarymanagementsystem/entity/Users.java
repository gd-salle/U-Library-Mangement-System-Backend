package com.university.librarymanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "library_card_number", nullable = false, unique = true)
    private String libraryCardNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "school_id", nullable = false, unique = true)
    private String schoolId;

    @Column(name = "user_type", nullable = false)
    private String userType;

}
