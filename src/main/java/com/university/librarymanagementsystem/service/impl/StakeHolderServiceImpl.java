package com.university.librarymanagementsystem.service.impl;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.StakeholdersDto;
import com.university.librarymanagementsystem.entity.StakeHolders;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.StakeHolderMapper;
import com.university.librarymanagementsystem.repository.StakeHolderRepository;
import com.university.librarymanagementsystem.service.StakeHolderService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StakeHolderServiceImpl implements StakeHolderService {

    private StakeHolderRepository stakeHolderRepository;

    @Override
    public StakeholdersDto getStakeholderById(String id) {
        StakeHolders stakeHolders = stakeHolderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return StakeHolderMapper.mapToStakeHoldersDto(stakeHolders);
    }

    @Override
    public StakeholdersDto createStakeholder(StakeholdersDto stakeholdersDto) {

        StakeHolders stakeHolders = StakeHolderMapper.mapToStakeHolderDto(stakeholdersDto);
        StakeHolders savedStakeHolders = stakeHolderRepository.save(stakeHolders);
        return StakeHolderMapper.mapToStakeHoldersDto(savedStakeHolders);
    }

}
