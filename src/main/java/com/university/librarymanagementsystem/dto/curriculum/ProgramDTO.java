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
public class ProgramDTO {
    private int id;
    @JsonProperty("department_id")
    private int department_id;
    private String department_name;
    private int department_status;
    private String name;
    private int subjects;
    private int status;
}
