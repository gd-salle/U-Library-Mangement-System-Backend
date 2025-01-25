package com.university.librarymanagementsystem.repository.catalog;

import com.university.librarymanagementsystem.dto.catalog.BookSearchRequest;
import com.university.librarymanagementsystem.entity.catalog.Author;
import com.university.librarymanagementsystem.entity.catalog.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> advancedSearchBooks(BookSearchRequest request) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);
        Join<Book, Author> author = book.join("authors", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        // Handle criteria with operators
        for (BookSearchRequest.SearchCriterion criterion : request.getCriteria()) {
            String idx = criterion.getIdx();
            String searchTerm = criterion.getSearchTerm();
            String operator = criterion.getOperator();

            Predicate predicate = null;
            switch (idx) {
                case "q":
                    predicate = cb.or(
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("title")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("description")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))),
                            cb.equal(cb.function("SOUNDEX", String.class, author.get("name")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("publisher")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("categories")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("subjects")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))),
                            // LIKE for substring matches
                            cb.like(cb.lower(book.get("title")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.like(cb.lower(book.get("description")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.like(cb.lower(author.get("name")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.like(cb.lower(book.get("publisher")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.like(cb.lower(book.get("categories")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.like(cb.lower(book.get("subjects")), "%" + searchTerm.toLowerCase() + "%"));
                    break;
                case "intitle":
                    predicate = cb.or(
                            cb.like(cb.lower(book.get("title")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("title")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))));
                    break;

                case "inauthor":
                    predicate = cb.or(
                            cb.like(cb.lower(author.get("name")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.equal(cb.function("SOUNDEX", String.class, author.get("name")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))));
                    break;

                case "inpublisher":
                    predicate = cb.or(
                            cb.like(cb.lower(book.get("publisher")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("publisher")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))));
                    break;

                case "insubjects":
                    predicate = cb.or(
                            cb.like(cb.lower(book.get("subjects")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("subjects")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))));
                    break;

                case "isbn":
                    predicate = cb.or(
                            cb.like(cb.lower(book.get("isbn10")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.like(cb.lower(book.get("isbn13")), "%" + searchTerm.toLowerCase() + "%"),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("isbn10")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))),
                            cb.equal(cb.function("SOUNDEX", String.class, book.get("isbn13")),
                                    cb.function("SOUNDEX", String.class, cb.literal(searchTerm))));
                    break;

                default:
                    continue;
            }

            if ("AND".equalsIgnoreCase(operator)) {
                predicates.add(predicate);
            } else if ("OR".equalsIgnoreCase(operator)) {
                predicates.add(cb.or(predicate));
            } else if ("NOT".equalsIgnoreCase(operator)) {
                predicates.add(cb.not(predicate));
            }
        }

        LocalDate startDate = null;
        LocalDate endDate = null;
        if (request.getYearRange() != null && !request.getYearRange().isEmpty()) {
            String[] years = request.getYearRange().split("-");
            try {
                startDate = LocalDate.parse(years[0].trim() + "-01-01");
                endDate = LocalDate.parse(years[1].trim() + "-12-31");
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid year range format. Use YYYY-YYYY.");
            }
        }

        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(book.get("dateAcquired"), startDate));
        }
        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(book.get("dateAcquired"), endDate));
        }
        if (request.getLanguage() != null) {
            predicates.add(cb.equal(book.get("language"), request.getLanguage()));
        }
        if (Boolean.TRUE.equals(request.getIsAvailableOnly())) {
            predicates.add(cb.equal(book.get("status"), "Available"));
        }

        // Combine all predicates
        query.where(cb.and(predicates.toArray(new Predicate[0])));

        // Sorting logic
        if ("Title A-Z".equalsIgnoreCase(request.getSortOrder())) {
            query.orderBy(cb.asc(book.get("title")));
        } else if ("Title Z-A".equalsIgnoreCase(request.getSortOrder())) {
            query.orderBy(cb.desc(book.get("title")));
        } else if ("Acquisition date: newest to oldest".equalsIgnoreCase(request.getSortOrder())) {
            query.orderBy(cb.desc(book.get("dateAcquired")));
        } else if ("Acquisition date: oldest to newest".equalsIgnoreCase(request.getSortOrder())) {
            query.orderBy(cb.asc(book.get("dateAcquired")));
        }

        return entityManager.createQuery(query).getResultList();
    }

}
