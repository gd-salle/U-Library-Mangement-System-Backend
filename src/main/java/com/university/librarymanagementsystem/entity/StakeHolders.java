package com.university.librarymanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "stakeholders")
public class StakeHolders {

    @Id
    private String id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name", nullable = true)
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "suffix", nullable = true)
    private String suffix;

    @Column(name = "department", nullable = false)
    private int department;

    @Column(name = "course", nullable = false)
    private int course;

    @Column(name = "contact_num", nullable = false)
    private String contactNum;

    @Column(name = "email_add", nullable = false)
    private String emailAdd;

    @Column(name = "status", nullable = false)
    private String status;

}
