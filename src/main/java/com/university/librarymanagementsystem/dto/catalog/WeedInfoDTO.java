package com.university.librarymanagementsystem.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeedInfoDTO {

    private Long id;
    private String accessionNo;
    private String callNumber;
    private String title;
    private String publishedDate;

}
