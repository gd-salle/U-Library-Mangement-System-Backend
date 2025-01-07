package com.university.librarymanagementsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.university.librarymanagementsystem.dto.FineDto;
import com.university.librarymanagementsystem.entity.Fine;

public interface FineService {

    List<Fine> calculateAndSaveFines();

    List<FineDto> getAllFines();
}
