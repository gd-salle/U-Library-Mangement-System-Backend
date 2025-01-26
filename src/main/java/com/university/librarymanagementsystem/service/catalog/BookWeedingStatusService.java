package com.university.librarymanagementsystem.service.catalog;

import java.util.List;

import com.university.librarymanagementsystem.dto.catalog.BookWeedingStatusDTO;

public interface BookWeedingStatusService {

    BookWeedingStatusDTO createBookWeedingStatus(BookWeedingStatusDTO bookWeedingStatusDTO);

    BookWeedingStatusDTO updateBookWeedingStatus(Long id, BookWeedingStatusDTO bookWeedingStatusDTO);

    void deleteBookWeedingStatus(Long id);

    List<BookWeedingStatusDTO> getAllBookWeedingStatuses();

    BookWeedingStatusDTO getBookWeedingStatusById(Long id);
}
