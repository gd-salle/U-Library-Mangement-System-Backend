package com.university.librarymanagementsystem.service;

import com.university.librarymanagementsystem.dto.StakeholdersDto;

public interface StakeHolderService {

    StakeholdersDto getStakeholderById(String id);

    StakeholdersDto getStudentWithDepartmentAndCourse(String studentId);
}
