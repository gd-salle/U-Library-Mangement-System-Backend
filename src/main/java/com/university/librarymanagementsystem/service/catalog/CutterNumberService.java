package com.university.librarymanagementsystem.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.catalog.CutterSanbornNumberDto;
import com.university.librarymanagementsystem.entity.catalog.CutterSanbornNumber;
import com.university.librarymanagementsystem.repository.catalog.CutterNumberRepository;

@Service
public class CutterNumberService {

    @Autowired
    private CutterNumberRepository cutterNumberRepository;

    public CutterSanbornNumber saveCutterSanbornNumber(CutterSanbornNumberDto cutterSanbornNumberDto) {
        CutterSanbornNumber cn = new CutterSanbornNumber();
        cn.setCutterNumber(cutterSanbornNumberDto.getCutterNumber());
        cn.setAuthorName(cutterSanbornNumberDto.getAuthorName());
        return cutterNumberRepository.save(cn);
    }
}
