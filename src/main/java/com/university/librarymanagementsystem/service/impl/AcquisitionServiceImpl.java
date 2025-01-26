package com.university.librarymanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.AcquisitionDTO;
import com.university.librarymanagementsystem.entity.Acquisition;
import com.university.librarymanagementsystem.mapper.AcquisitionMapper;
import com.university.librarymanagementsystem.repository.AcquisitionRepository;
import com.university.librarymanagementsystem.service.AcquisitionService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AcquisitionServiceImpl implements AcquisitionService {

    private AcquisitionRepository acquisitionRepository;

    @Override
    public List<AcquisitionDTO> addRecords(List<AcquisitionDTO> acquisitionDTOs) {

        // add handler of unfinished cataloging where if there the current newly
        // acquired books havent cataloged all then it will not allow new import.

        acquisitionDTOs.forEach(dto -> {
            // Set a default value for status, e.g., 0 (not added) or 1 (added)
            dto.setStatus(0);
        });

        // Map DTOs to entities and save them
        List<Acquisition> acquisitions = acquisitionDTOs.stream()
                .map(AcquisitionMapper::mapToAcquisition)
                .collect(Collectors.toList());

        List<Acquisition> savedAcquisitions = acquisitionRepository.saveAll(acquisitions);

        // Map entities back to DTOs and return the result
        return savedAcquisitions.stream()
                .map(AcquisitionMapper::mapToAcquisitionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AcquisitionDTO> getPendingCatalogRecords() {
        // Fetch the pending records from the repository
        List<Acquisition> pendingRecords = acquisitionRepository.getPendingRecords();

        // Return the list of pending catalog records
        return pendingRecords.stream().map((pendingRecord) -> AcquisitionMapper.mapToAcquisitionDTO(pendingRecord))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional // Ensures the update query runs within a transaction
    public boolean updateStatus(Integer id) {
        return acquisitionRepository.updatePendingStatus(id) > 0; // Ensure the update count is > 0 for success
    }

}
