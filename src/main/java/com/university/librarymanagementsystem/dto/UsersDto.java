package com.university.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {

    private Long userId;
    private String libraryCardNumber;
    private String password;
    private String schoolId;
    private String userType;

}
