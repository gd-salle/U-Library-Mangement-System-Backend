package com.university.librarymanagementsystem.service.curriculum;

import java.util.List;

import com.university.librarymanagementsystem.dto.curriculum.BookReferenceDTO;

public interface BookReferenceService {
    BookReferenceDTO addBookReference(BookReferenceDTO bookRefDTO);

    List<BookReferenceDTO> getAllBookReference();

    List<BookReferenceDTO> getAllBookRefBySubject(Integer subjectId);

    BookReferenceDTO updateBookRef(Integer bookRefId, BookReferenceDTO updatedBookRef);
}
