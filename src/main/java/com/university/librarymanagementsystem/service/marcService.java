package com.university.librarymanagementsystem.service;

import com.university.librarymanagementsystem.dto.marcDTO;

public interface marcService {
    marcDTO addMARCData(marcDTO marcDTO);

    marcDTO getMARCDataByISBN(String isbn);
}
