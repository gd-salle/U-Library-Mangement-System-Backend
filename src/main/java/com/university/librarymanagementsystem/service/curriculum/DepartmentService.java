package com.university.librarymanagementsystem.service.curriculum;

import java.util.List;

import com.university.librarymanagementsystem.dto.curriculum.DepartmentDTO;

public interface DepartmentService {
    DepartmentDTO addDepartment(DepartmentDTO departmentDTO);

    DepartmentDTO getDepartmentById(Integer departmentID);

    List<DepartmentDTO> getAllDepartments();

    DepartmentDTO updateDepartment(Integer departmentID, DepartmentDTO updateDepartment);
}
