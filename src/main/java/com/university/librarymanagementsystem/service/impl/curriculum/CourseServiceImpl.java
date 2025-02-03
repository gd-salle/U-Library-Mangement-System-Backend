package com.university.librarymanagementsystem.service.impl.curriculum;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.curriculum.CourseDTO;
import com.university.librarymanagementsystem.entity.curriculum.Course;
import com.university.librarymanagementsystem.entity.curriculum.Curriculum;
import com.university.librarymanagementsystem.exception.DuplicateEntryException;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.curriculum.CourseMapper;
import com.university.librarymanagementsystem.repository.curriculum.CurriculumRepository;
import com.university.librarymanagementsystem.repository.curriculum.CourseRepository;
import com.university.librarymanagementsystem.service.curriculum.CourseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private CurriculumRepository currRepository;

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        Curriculum curriculum = currRepository.findById(courseDTO.getCurr_id()).orElseThrow(
                () -> new ResourceNotFoundException("Curriculum not found for id:" + courseDTO.getCurr_id()));

        Course course = CourseMapper.maptoSubject(courseDTO);

        // boolean isExisting =
        // courseRepository.existsByName(courseDTO.getCourse_name());

        // if (isExisting) {
        // throw new DuplicateEntryException("A Course with the same name is already
        // existed!");
        // }

        course.setCurriculum(curriculum);

        return CourseMapper.mapToCourseDTO(course);
    }
}