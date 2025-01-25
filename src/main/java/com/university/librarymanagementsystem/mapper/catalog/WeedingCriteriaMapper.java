package com.university.librarymanagementsystem.mapper.catalog;

import com.university.librarymanagementsystem.dto.catalog.WeedingCriteriaDTO;
import com.university.librarymanagementsystem.entity.catalog.WeedingCriteria;

public class WeedingCriteriaMapper {

    public static WeedingCriteriaDTO mapToWeedingCriteriaDto(WeedingCriteria weedingCriteria) {
        return new WeedingCriteriaDTO(
                weedingCriteria.getId(),
                weedingCriteria.getDdc(),
                weedingCriteria.getMinYearsOld(),
                weedingCriteria.getConditionThreshold(),
                weedingCriteria.getUsageThreshold(),
                weedingCriteria.getLanguage(),
                weedingCriteria.getDuplicationCheck());
    }

    public static WeedingCriteria mapToWeedingCriteriaEntity(WeedingCriteriaDTO weedingCriteriaDTO) {
        return new WeedingCriteria(
                weedingCriteriaDTO.getId(),
                weedingCriteriaDTO.getDdc(),
                weedingCriteriaDTO.getMinYearsOld(),
                weedingCriteriaDTO.getConditionThreshold(),
                weedingCriteriaDTO.getUsageThreshold(),
                weedingCriteriaDTO.getLanguage(),
                weedingCriteriaDTO.getDuplicationCheck());
    }
}