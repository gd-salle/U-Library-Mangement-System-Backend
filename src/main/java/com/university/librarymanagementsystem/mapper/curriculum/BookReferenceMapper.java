package com.university.librarymanagementsystem.mapper.curriculum;

import org.springframework.stereotype.Component;

import com.university.librarymanagementsystem.dto.curriculum.BookReferenceDTO;
import com.university.librarymanagementsystem.entity.curriculum.BookReference;
import com.university.librarymanagementsystem.entity.curriculum.Subject;

@Component
public class BookReferenceMapper {

    public static BookReferenceDTO mapToBookRefDTO(BookReference bookRef) {
        return new BookReferenceDTO(
                bookRef.getId(),
                bookRef.getSubject().getId(),
                bookRef.getSubject().getName(),
                bookRef.getBook_name(),
                bookRef.getStatus(),
                bookRef.getUrlPath());

    }

    public static BookReference mapTBookRef(BookReferenceDTO bookRefDTO) {
        Subject subject = new Subject();

        subject.setId(bookRefDTO.getSubject_id());

        return new BookReference(
                bookRefDTO.getId(),
                subject,
                bookRefDTO.getBook_name(),
                bookRefDTO.getStatus(),
                bookRefDTO.getUrlPath());
    }
}
