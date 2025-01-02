package com.university.librarymanagementsystem.service.impl.curriculum;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.curriculum.DepartmentDTO;
import com.university.librarymanagementsystem.entity.curriculum.Department;
import com.university.librarymanagementsystem.exception.DuplicateEntryException;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.curriculum.DepartmentMapper;
import com.university.librarymanagementsystem.repository.curriculum.DepartmentRepository;
import com.university.librarymanagementsystem.service.curriculum.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.mapToDepartment(departmentDTO);

        boolean isExisting = departmentRepository.existsByName(departmentDTO.getName());

        if (isExisting) {
            throw new DuplicateEntryException("A department with the same name is already existed!");
        }
        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Integer departmentID) {
        Department department = departmentRepository.findById(departmentID)
                .orElseThrow(() -> new ResourceNotFoundException("not exisiting" + departmentID));

        return DepartmentMapper.mapToDepartmentDTO(department);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDTO(department))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO updateDepartment(Integer departmentID, DepartmentDTO updatedDepartment) {

        Department department = departmentRepository.findById(departmentID).orElseThrow(
                () -> new ResourceNotFoundException("Employee is not existing with given ID:" + departmentID));

        department.setName(updatedDepartment.getName());
        department.setStatus(updatedDepartment.getStatus());

        Department updatedDepartmentObj = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDTO(updatedDepartmentObj);
    }
}
