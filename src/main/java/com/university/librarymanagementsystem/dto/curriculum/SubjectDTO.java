package com.university.librarymanagementsystem.dto.curriculum;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDTO {
    private int id;
    @JsonProperty("program_id")
    private int program_id;
    private String program_name;

    @JsonProperty("department_id")
    private int department_id;
    private String department_name;

    private String subject_name;
    private int year;
    private int status;
}
