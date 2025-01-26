package com.university.librarymanagementsystem.service.catalog;

import java.util.List;

import com.university.librarymanagementsystem.dto.catalog.WeedingCriteriaDTO;

public interface WeedingCriteriaService {

    WeedingCriteriaDTO createWeedingCriteria(WeedingCriteriaDTO weedingCriteriaDTO);

    WeedingCriteriaDTO updateWeedingCriteria(WeedingCriteriaDTO weedingCriteriaDTO);

    void deleteWeedingCriteria(Integer id);

    List<WeedingCriteriaDTO> getAllWeedingCriteria();

    WeedingCriteriaDTO getWeedingCriteriaById(Integer id);

}
