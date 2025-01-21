package com.university.librarymanagementsystem.mapper.user;

import com.university.librarymanagementsystem.dto.user.StakeholdersDto;
import com.university.librarymanagementsystem.entity.user.StakeHolders;

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
                stakeholdersDto.getProgram(),
                stakeholdersDto.getContactNum(),
                stakeholdersDto.getEmailAdd(),
                stakeholdersDto.getStatus());
    }

}
