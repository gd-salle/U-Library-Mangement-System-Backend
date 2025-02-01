package com.university.librarymanagementsystem.service.catalog;

import java.util.List;

import com.university.librarymanagementsystem.dto.catalog.BookWeedingStatusDTO;
import com.university.librarymanagementsystem.dto.catalog.WeedInfoDTO;

public interface BookWeedingStatusService {

    BookWeedingStatusDTO createBookWeedingStatus(BookWeedingStatusDTO bookWeedingStatusDTO);

    // BookWeedingStatusDTO updateBookWeedingStatus(Long id, BookWeedingStatusDTO
    // bookWeedingStatusDTO);
    void updateBookWeedingStatus(WeedInfoDTO weedInfoDTO);

    void deleteBookWeedingStatus(Long id);

    List<WeedInfoDTO> getAllBookWeedingStatuses();

    BookWeedingStatusDTO getBookWeedingStatusById(Long id);
}
