package com.university.librarymanagementsystem.mapper;

import org.springframework.stereotype.Component;

import com.university.librarymanagementsystem.dto.AcquisitionDTO;
import com.university.librarymanagementsystem.entity.Acquisition;

@Component
public class AcquisitionMapper {
    public static AcquisitionDTO mapToAcquisitionDTO(Acquisition acquisition) {
        return new AcquisitionDTO(
                acquisition.getId(),
                acquisition.getBook_title(),
                acquisition.getIsbn(),
                acquisition.getPublisher(),
                acquisition.getEdition(),
                acquisition.getSeries(),
                acquisition.getPurchase_price(),
                acquisition.getPurchase_date(),
                acquisition.getAcquired_date(),
                acquisition.getVendor_name(),
                acquisition.getVendor_location(),
                acquisition.getFunding_source(),
                acquisition.getStatus());

    }

    public static Acquisition mapToAcquisition(AcquisitionDTO acquisitionDTO) {
        return new Acquisition(
                acquisitionDTO.getId(),
                acquisitionDTO.getBook_title(),
                acquisitionDTO.getIsbn(),
                acquisitionDTO.getPublisher(),
                acquisitionDTO.getEdition(),
                acquisitionDTO.getSeries(),
                acquisitionDTO.getPurchase_price(),
                acquisitionDTO.getPurchase_date(),
                acquisitionDTO.getAcquired_date(),
                acquisitionDTO.getVendor_name(),
                acquisitionDTO.getVendor_location(),
                acquisitionDTO.getFunding_source(),
                acquisitionDTO.getStatus());
    }
}
