package com.university.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.CutterSanbornNumberDto;
import com.university.librarymanagementsystem.entity.CutterSanbornNumber;
import com.university.librarymanagementsystem.repository.CutterNumberRepository;

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
