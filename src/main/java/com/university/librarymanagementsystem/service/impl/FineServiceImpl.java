package com.university.librarymanagementsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.FineDto;
import com.university.librarymanagementsystem.entity.Fine;
import com.university.librarymanagementsystem.mapper.FineMapper;
import com.university.librarymanagementsystem.repository.FineRepository;
import com.university.librarymanagementsystem.service.FineService;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineRepository fineRepository;

    @Override
    public List<FineDto> getAllFineDetails() {
        List<Fine> fines = fineRepository.findAll();
        return fines.stream()
                .map(FineMapper::toDTO)
                .toList();
    }

}
