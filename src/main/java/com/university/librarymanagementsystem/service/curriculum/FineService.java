package com.university.librarymanagementsystem.service.curriculum;

import java.time.LocalDateTime;
import java.util.List;

import com.university.librarymanagementsystem.dto.circulation.FineDto;
import com.university.librarymanagementsystem.entity.circulation.Fine;

public interface FineService {

    List<Fine> calculateAndSaveFines();

    List<FineDto> getAllFines();
}
