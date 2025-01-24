package com.university.librarymanagementsystem.service.impl;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.marcDTO;
import com.university.librarymanagementsystem.entity.marc;
import com.university.librarymanagementsystem.mapper.marcMapper;
import com.university.librarymanagementsystem.repository.marcRepo;
import com.university.librarymanagementsystem.service.marcService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class marcServiceImpl implements marcService {

    private marcRepo repo;

    @Override
    public marcDTO addMARCData(marcDTO marcDTO) {

        marc marc = marcMapper.maptomMarc(marcDTO);

        return marcMapper.maptomMarcDTO(repo.save(marc));
    }

    @Override
    public marcDTO getMARCDataByISBN(String isbn) {
        marc marc = repo.findByISBN(isbn);
        if (marc == null) {
            throw new IllegalArgumentException("MARC record with ISBN " + isbn + " not found");
        }
        return marcMapper.maptomMarcDTO(marc);
    }
}
