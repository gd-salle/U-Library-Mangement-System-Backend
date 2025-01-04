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

public class BookReferenceDTO {
    private int id;

    @JsonProperty("subject_id")
    private int subject_id;
    private String subject_name;

    // @JsonProperty("books_id")
    // private Long book_id;
    private String book_name;

    private int status;

    private String urlPath;
}
