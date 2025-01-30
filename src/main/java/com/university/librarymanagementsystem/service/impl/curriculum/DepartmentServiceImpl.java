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
    public List<DepartmentDTO> addDepartments(List<DepartmentDTO> departmentDTOs) {
        List<Department> departments = departmentDTOs.stream()
                .map(DepartmentMapper::mapToDepartment)
                .collect(Collectors.toList());

        // Filter out duplicates before saving
        List<Department> nonDuplicateDepartments = departments.stream()
                .filter(department -> !departmentRepository.existsByName(department.getName()))
                .collect(Collectors.toList());

        if (nonDuplicateDepartments.isEmpty()) {
            throw new DuplicateEntryException("All provided departments already exist.");
        }

        List<Department> savedDepartments = departmentRepository.saveAll(nonDuplicateDepartments);

        return savedDepartments.stream()
                .map(DepartmentMapper::mapToDepartmentDTO)
                .collect(Collectors.toList());
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
