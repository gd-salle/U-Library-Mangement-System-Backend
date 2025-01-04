package com.university.librarymanagementsystem.service.impl.curriculum;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.curriculum.BookReferenceDTO;
// import com.university.librarymanagementsystem.entity.Book;
import com.university.librarymanagementsystem.entity.curriculum.BookReference;
import com.university.librarymanagementsystem.entity.curriculum.Subject;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.curriculum.BookReferenceMapper;
// import com.university.librarymanagementsystem.repository.BookRepository;
import com.university.librarymanagementsystem.repository.curriculum.BookReferenceRepository;
import com.university.librarymanagementsystem.repository.curriculum.SubjectRepository;
import com.university.librarymanagementsystem.service.curriculum.BookReferenceService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookReferenceServiceImpl implements BookReferenceService {

    // private BookRepository bookRepository;
    private SubjectRepository subjectRepository;
    private BookReferenceRepository bookRefRepository;

    @Override
    public BookReferenceDTO addBookReference(BookReferenceDTO bookRefDTO) {
        System.out.println("SUBJECT ID:" + bookRefDTO.getSubject_id());
        Subject subject = subjectRepository.findById(bookRefDTO.getSubject_id())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found!"));

        // Book book = bookRepository.findById(bookRefDTO.getBook_id())
        // .orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

        BookReference bookRef = BookReferenceMapper.mapTBookRef(bookRefDTO);

        bookRef.setSubject(subject);
        // bookRef.setBook(book);

        BookReference savedBookRef = bookRefRepository.save(bookRef);

        System.out.println("test:" + savedBookRef);
        return BookReferenceMapper.mapToBookRefDTO(savedBookRef);
    }

    @Override
    public List<BookReferenceDTO> getAllBookReference() {
        List<BookReference> bookReferences = bookRefRepository.findAll();

        return bookReferences.stream().map((bookRef) -> BookReferenceMapper.mapToBookRefDTO(bookRef))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookReferenceDTO> getAllBookRefBySubject(Integer subjectId) {
        List<BookReference> bookReferences = bookRefRepository.findAllBookReferenceBySubject(subjectId);

        return bookReferences.stream().map((bookRef) -> BookReferenceMapper.mapToBookRefDTO(bookRef))
                .collect(Collectors.toList());
    }

    @Override
    public BookReferenceDTO updateBookRef(Integer bookRefId, BookReferenceDTO updatedBookRef) {
        BookReference bookRef = bookRefRepository.findById(bookRefId)
                .orElseThrow(() -> new ResourceNotFoundException("Book Reference doesnt exist"));

        Subject subject = subjectRepository.findById(updatedBookRef.getSubject_id())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found!"));

        // Book book = bookRepository.findById(updatedBookRef.getBook_id())
        // .orElseThrow(() -> new ResourceNotFoundException("Book not found!"));

        // bookRef.setBook(book);
        bookRef.setSubject(subject);
        bookRef.setStatus(updatedBookRef.getStatus());

        BookReference updatedEntity = bookRefRepository.save(bookRef);

        return BookReferenceMapper.mapToBookRefDTO(updatedEntity);
    }
}
