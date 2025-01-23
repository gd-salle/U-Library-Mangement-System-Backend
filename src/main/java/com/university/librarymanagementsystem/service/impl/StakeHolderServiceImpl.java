package com.university.librarymanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.StakeholdersDto;
import com.university.librarymanagementsystem.entity.StakeHolders;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.StakeHolderMapper;
import com.university.librarymanagementsystem.repository.StakeHolderRepository;
import com.university.librarymanagementsystem.service.StakeHolderService;

@Service
public class StakeHolderServiceImpl implements StakeHolderService {

    @Autowired
    private StakeHolderRepository stakeHolderRepository;

    @Override
    public StakeholdersDto getStakeholderById(String id) {

        StakeHolders stakeHolders = stakeHolderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));

        return StakeHolderMapper.mapToStakeHoldersDto(stakeHolders);
    }

    @Override
    public StakeholdersDto getStudentWithDepartmentAndCourse(String studentId) {
        StakeHolders stakeHolders = stakeHolderRepository.findStakeholderById(studentId);
        
        if (stakeHolders == null) {
            throw new ResourceNotFoundException("Not Found: " + studentId);
        }
        System.out.println(stakeHolders.getEmailAdd());
        return StakeHolderMapper.mapToStakeHoldersDto(stakeHolders);
    }

}
