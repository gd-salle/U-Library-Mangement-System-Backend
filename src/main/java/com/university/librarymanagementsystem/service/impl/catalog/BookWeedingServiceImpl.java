package com.university.librarymanagementsystem.service.impl.catalog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.entity.catalog.Book;
import com.university.librarymanagementsystem.entity.catalog.BookWeeding;
import com.university.librarymanagementsystem.entity.catalog.BookWeedingStatus;
import com.university.librarymanagementsystem.entity.catalog.WeedingCriteria;
import com.university.librarymanagementsystem.enums.ProcessStatus;
import com.university.librarymanagementsystem.enums.WeedStatus;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.repository.catalog.BookRepository;
import com.university.librarymanagementsystem.repository.catalog.BookWeedingRepository;
import com.university.librarymanagementsystem.repository.catalog.BookWeedingStatusRepository;
import com.university.librarymanagementsystem.repository.catalog.WeedingCriteriaRepository;
import com.university.librarymanagementsystem.repository.circulation.LoanRepository;
import com.university.librarymanagementsystem.service.catalog.BookWeedingService;

@Service
public class BookWeedingServiceImpl implements BookWeedingService {

    @Autowired
    private BookWeedingRepository bookWeedingRepository;

    @Autowired
    private WeedingCriteriaRepository weedingCriteriaRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookWeedingStatusRepository bookWeedingStatusRepository;

    @Override
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void annualWeedingFlagging() {
        initiateWeedingProcess("System");
    }

    @Override
    public void manualWeedingFlagging() {
        initiateWeedingProcess("Manual");
    }

    private void initiateWeedingProcess(String initiator) {
        BookWeeding weed = new BookWeeding();
        weed.setStartDate(LocalDate.now());
        weed.setStatus(ProcessStatus.SCHEDULED);
        weed.setInitiator(initiator);
        BookWeeding savedWeed = bookWeedingRepository.save(weed);
        flagBooksForWeeding(savedWeed.getId());
    }

    @Override
    public void flagBooksForWeeding(Long processId) {
        BookWeeding weed = bookWeedingRepository.findById(processId)
                .orElseThrow(() -> new ResourceNotFoundException("Book Weeding not found with id: " + processId));

        List<WeedingCriteria> criteriaList = weedingCriteriaRepository.findAllByCriteriaStatusTrue();

        for (WeedingCriteria criteria : criteriaList) {
            int ddcStart = Integer.parseInt(criteria.getDdc());
            int ddcEnd = ddcStart + 99;
            List<Book> books = bookRepository.findByDdcAndLanguage(ddcStart, ddcEnd, criteria.getLanguage());

            for (Book book : books) {
                if (shouldBeWeeded(book, criteria)) {
                    flagBook(book, criteria, weed);
                }
            }
        }
    }

    private boolean shouldBeWeeded(Book book, WeedingCriteria criteria) {

        int bookAge = 0;

        try {
            // Assuming the book's published date is in "yyyy-MM-dd" format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate publishedDate = LocalDate.parse(book.getPublishedDate(), formatter);

            // Get the current year and calculate the book's age
            int currentYear = LocalDate.now().getYear();
            bookAge = currentYear - publishedDate.getYear();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle parsing or calculation errors here (e.g., invalid date format)
        }

        boolean conditionCheck = book.getStatus().toLowerCase()
                .contains(criteria.getConditionThreshold().toLowerCase());
        LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);
        long borrowCount = loanRepository.countLoansByBookIdWithinLastYear(book.getId(), oneYearAgo);
        boolean usageCheck = borrowCount < criteria.getUsageThreshold();
        boolean ageCheck = bookAge >= criteria.getMinYearsOld();
        boolean duplication = !criteria.getDuplicationCheck() ||
                (bookRepository.getTotalDuplicatedBooks(
                        book.getTitle(), book.getIsbn10(), book.getIsbn13()) != null &&
                        bookRepository.getTotalDuplicatedBooks(
                                book.getTitle(), book.getIsbn10(), book.getIsbn13()) == 0);

        return conditionCheck && usageCheck && ageCheck && duplication;
    }

    @Override
    public void flagBook(Book book, WeedingCriteria weedingCriteria, BookWeeding bookWeeding) {
        Optional<BookWeedingStatus> existingBookWeedingStatus = bookWeedingStatusRepository.findByBookId(book.getId());

        if (existingBookWeedingStatus.isPresent()) {
            BookWeedingStatus weedingStatus = existingBookWeedingStatus.get();
            if (weedingStatus.getWeedStatus() != WeedStatus.FLAGGED) {
                // Update existing status if not already flagged
                weedingStatus.setWeedStatus(WeedStatus.FLAGGED);
                weedingStatus.setReviewDate(LocalDate.now());
                weedingStatus.setWeedingCriteria(weedingCriteria);
                weedingStatus.setBookWeeding(bookWeeding);
                bookWeedingStatusRepository.save(weedingStatus);
            }
            // If the book is already flagged, we do nothing here
        } else {
            // If no existing weeding status, create a new one
            BookWeedingStatus newStatus = new BookWeedingStatus();
            newStatus.setBook(book);
            newStatus.setWeedingCriteria(weedingCriteria);
            newStatus.setWeedStatus(WeedStatus.FLAGGED);
            newStatus.setReviewDate(LocalDate.now());
            newStatus.setBookWeeding(bookWeeding);
            bookWeedingStatusRepository.save(newStatus);
        }
    }

}
