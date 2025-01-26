package com.university.librarymanagementsystem.mapper.catalog;

import com.university.librarymanagementsystem.dto.catalog.BookWeedingStatusDTO;
import com.university.librarymanagementsystem.entity.catalog.BookWeedingStatus;
import com.university.librarymanagementsystem.enums.WeedStatus;

public class BookWeedingStatusMapper {

    public static BookWeedingStatusDTO mapToDTO(BookWeedingStatus status) {
        return new BookWeedingStatusDTO(
                status.getId(),
                status.getBook().getId(),
                status.getWeedingCriteria().getId(),
                status.getWeedStatus().name(),
                status.getReviewDate(),
                status.getNotes());
    }

    public static BookWeedingStatus mapToEntity(BookWeedingStatusDTO dto) {
        BookWeedingStatus status = new BookWeedingStatus();
        status.setId(dto.getId());
        status.getBook().setId(dto.getBookId());
        status.getWeedingCriteria().setId(dto.getWeedingCriteriaId());
        status.setWeedStatus(WeedStatus.valueOf(dto.getWeedStatus()));
        status.setReviewDate(dto.getReviewDate());
        status.setNotes(dto.getNotes());
        return status;
    }
}
