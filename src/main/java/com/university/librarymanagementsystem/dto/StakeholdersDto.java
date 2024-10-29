package com.university.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
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
