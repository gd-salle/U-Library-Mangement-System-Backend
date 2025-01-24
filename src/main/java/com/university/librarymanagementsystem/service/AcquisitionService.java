package com.university.librarymanagementsystem.service;

import java.util.List;

import com.university.librarymanagementsystem.dto.AcquisitionDTO;

public interface AcquisitionService {
    List<AcquisitionDTO> addRecords(List<AcquisitionDTO> acquisitionDTOs);

    List<AcquisitionDTO> getPendingCatalogRecords();
}
