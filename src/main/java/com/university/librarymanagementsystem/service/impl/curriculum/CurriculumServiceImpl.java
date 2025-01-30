package com.university.librarymanagementsystem.service.impl.curriculum;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.curriculum.CurriculumDTO;
import com.university.librarymanagementsystem.entity.curriculum.Curriculum;
import com.university.librarymanagementsystem.mapper.curriculum.CurriculumMapper;
import com.university.librarymanagementsystem.repository.curriculum.CurriculumRepository;
import com.university.librarymanagementsystem.service.curriculum.CurriculumService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CurriculumServiceImpl implements CurriculumService {

    CurriculumRepository currRepo;

    public CurriculumDTO addCurriculum(CurriculumDTO currDTO) {
        Curriculum curr = CurriculumMapper.mapToCurriculum(currDTO);

        return CurriculumMapper.mapToCurriculumDTO(curr);
    }
}
