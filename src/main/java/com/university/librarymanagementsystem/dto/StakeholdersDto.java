package com.university.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StakeholdersDto {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private int department;
    private int course;
    private String contactNum;
    private String emailAdd;
    private String status;
}
