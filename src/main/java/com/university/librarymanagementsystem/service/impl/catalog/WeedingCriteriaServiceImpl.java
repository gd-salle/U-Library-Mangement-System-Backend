package com.university.librarymanagementsystem.service.impl.catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.catalog.WeedingCriteriaDTO;
import com.university.librarymanagementsystem.entity.catalog.WeedingCriteria;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.catalog.WeedingCriteriaMapper;
import com.university.librarymanagementsystem.repository.catalog.WeedingCriteriaRepository;
import com.university.librarymanagementsystem.service.catalog.WeedingCriteriaService;

@Service
public class WeedingCriteriaServiceImpl implements WeedingCriteriaService {

    @Autowired
    private WeedingCriteriaRepository weedingCriteriaRepository;

    @Override
    public WeedingCriteriaDTO createWeedingCriteria(WeedingCriteriaDTO weedingCriteriaDTO) {
        WeedingCriteria weedingCriteria = WeedingCriteriaMapper.mapToWeedingCriteriaEntity(weedingCriteriaDTO);
        WeedingCriteria savedCriteria = weedingCriteriaRepository.save(weedingCriteria);
        return WeedingCriteriaMapper.mapToWeedingCriteriaDto(savedCriteria);
    }

    @Override
    public WeedingCriteriaDTO updateWeedingCriteria(WeedingCriteriaDTO weedingCriteriaDTO) {
        WeedingCriteria existingCriteria = weedingCriteriaRepository.findById(
                weedingCriteriaDTO.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Weeding Criteria not found for id: " + weedingCriteriaDTO
                                .getId()));

        // Only update the fields that are not null in the request
        if (weedingCriteriaDTO.getDdc() != null) {
            existingCriteria.setDdc(weedingCriteriaDTO.getDdc());
        }
        if (weedingCriteriaDTO.getMinYearsOld() != null) {
            existingCriteria.setMinYearsOld(weedingCriteriaDTO.getMinYearsOld());
        }
        if (weedingCriteriaDTO.getConditionThreshold() != null) {
            existingCriteria.setConditionThreshold(weedingCriteriaDTO.getConditionThreshold());
        }
        if (weedingCriteriaDTO.getUsageThreshold() != null) {
            existingCriteria.setUsageThreshold(weedingCriteriaDTO.getUsageThreshold());
        }
        if (weedingCriteriaDTO.getLanguage() != null) {
            existingCriteria.setLanguage(weedingCriteriaDTO.getLanguage());
        }
        if (weedingCriteriaDTO.getDuplicationCheck() != null) {
            existingCriteria.setDuplicationCheck(weedingCriteriaDTO.getDuplicationCheck());
        }
        if (weedingCriteriaDTO.getCriteriaStatus() != null) {
            existingCriteria.setCriteriaStatus(weedingCriteriaDTO.getCriteriaStatus());
        }

        WeedingCriteria updatedCriteria = weedingCriteriaRepository.save(existingCriteria);
        return WeedingCriteriaMapper.mapToWeedingCriteriaDto(updatedCriteria);
    }

    @Override
    public void deleteWeedingCriteria(Integer id) {
        if (!weedingCriteriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Weeding Criteria not found for id: " + id);
        }
        weedingCriteriaRepository.deleteById(id);
    }

    @Override
    public List<WeedingCriteriaDTO> getAllWeedingCriteria() {
        return weedingCriteriaRepository.findAll().stream()
                .map(WeedingCriteriaMapper::mapToWeedingCriteriaDto)
                .toList();
    }

    @Override
    public WeedingCriteriaDTO getWeedingCriteriaById(Integer id) {
        WeedingCriteria existingCriteria = weedingCriteriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Weeding Criteria not found for id: " + id));

        return WeedingCriteriaMapper.mapToWeedingCriteriaDto(existingCriteria); // Return the mapped DTO
    }

}
