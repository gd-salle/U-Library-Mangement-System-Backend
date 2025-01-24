package com.university.librarymanagementsystem.mapper;

import org.springframework.stereotype.Component;
import com.university.librarymanagementsystem.dto.marcDTO;
import com.university.librarymanagementsystem.entity.marc;

@Component
public class marcMapper {

    public static marcDTO maptomMarcDTO(marc marc) {
        return new marcDTO(
                marc.getId(),
                marc.getIsbn(),
                marc.getCatSource(),
                marc.getLocCallNumber(),
                marc.getDdc(),
                marc.getPersonalName(),
                marc.getCorporateName(),
                marc.getTitle(),
                marc.getEdition(),
                marc.getPublisher(),
                marc.getPhysicalDesc(),
                marc.getContent(),
                marc.getMedia(),
                marc.getGender(),
                marc.getAssociatedLanguage(),
                marc.getSeries(),
                marc.getBiblio(),
                marc.getContentNote(),
                marc.getProductionNote(),
                marc.getSummary(),
                marc.getTargetAudience(),
                marc.getFundingSource(),
                marc.getTropicalTerm(),
                marc.getHistoricalData(),
                marc.getOtherAuthors());
    }

    public static marc maptomMarc(marcDTO marcDTO) {
        return new marc(
                marcDTO.getId(),
                marcDTO.getIsbn(),
                marcDTO.getCatSource(),
                marcDTO.getLocCallNumber(),
                marcDTO.getDdc(),
                marcDTO.getPersonalName(),
                marcDTO.getCorporateName(),
                marcDTO.getTitle(),
                marcDTO.getEdition(),
                marcDTO.getPublisher(),
                marcDTO.getPhysicalDesc(),
                marcDTO.getContent(),
                marcDTO.getMedia(),
                marcDTO.getGender(),
                marcDTO.getAssociatedLanguage(),
                marcDTO.getSeries(),
                marcDTO.getBiblio(),
                marcDTO.getContentNote(),
                marcDTO.getProductionNote(),
                marcDTO.getSummary(),
                marcDTO.getTargetAudience(),
                marcDTO.getFundingSource(),
                marcDTO.getTropicalTerm(),
                marcDTO.getHistoricalData(),
                marcDTO.getOtherAuthors());
    }
}
