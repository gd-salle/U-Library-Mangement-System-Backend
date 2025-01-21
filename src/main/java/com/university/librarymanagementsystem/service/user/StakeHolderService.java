package com.university.librarymanagementsystem.service.user;

import com.university.librarymanagementsystem.dto.user.StakeholdersDto;

public interface StakeHolderService {

    StakeholdersDto getStakeholderById(String id);

    StakeholdersDto getStudentWithDepartmentAndCourse(String studentId);
}
