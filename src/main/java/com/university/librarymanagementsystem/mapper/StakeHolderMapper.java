package com.university.librarymanagementsystem.mapper;

import com.university.librarymanagementsystem.dto.StakeholdersDto;
import com.university.librarymanagementsystem.entity.StakeHolders;

public class StakeHolderMapper {

    public static StakeholdersDto mapToStakeHoldersDto(StakeHolders stakeholders) {
        return new StakeholdersDto(
                stakeholders.getId(),
                stakeholders.getFirstName(),
                stakeholders.getMiddleName(),
                stakeholders.getLastName(),
                stakeholders.getSuffix(),
                stakeholders.getDepartment(),
                stakeholders.getProgram(),
                stakeholders.getContactNum(),
                stakeholders.getEmailAdd(),
                stakeholders.getStatus());
    }

    public static StakeHolders mapToStakeHolderDto(StakeholdersDto stakeholdersDto) {
        return new StakeHolders(
                stakeholdersDto.getId(),
                stakeholdersDto.getFirstName(),
                stakeholdersDto.getMiddleName(),
                stakeholdersDto.getLastName(),
                stakeholdersDto.getSuffix(),
                stakeholdersDto.getDepartment(),
                stakeholdersDto.getCourse(),
                stakeholdersDto.getContactNum(),
                stakeholdersDto.getEmailAdd(),
                stakeholdersDto.getStatus());
    }

}
