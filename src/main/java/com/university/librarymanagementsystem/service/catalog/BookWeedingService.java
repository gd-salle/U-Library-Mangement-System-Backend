package com.university.librarymanagementsystem.service.catalog;

import com.university.librarymanagementsystem.entity.catalog.Book;
import com.university.librarymanagementsystem.entity.catalog.BookWeeding;
import com.university.librarymanagementsystem.entity.catalog.WeedingCriteria;

public interface BookWeedingService {

    void flagBooksForWeeding(Long processId);

    void annualWeedingFlagging();

    void manualWeedingFlagging();

    void flagBook(Book book, WeedingCriteria weedingCriteria, BookWeeding bookWeeding);
}
