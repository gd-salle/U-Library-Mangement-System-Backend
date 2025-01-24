package com.university.librarymanagementsystem.dto.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchRequest {

    private String keyword; // General keyword for broad search
    private List<SearchCriterion> criteria; // Detailed field-based search criteria
    private String yearRange; // Filter for year range (e.g., "2010-2020")
    private String language; // Language of the book
    private Boolean isAvailableOnly; // Filter for availability status
    private String individualLibrary; // Library filter
    private String sortOrder; // Sort order for results
    private List<String> itemType; // Filter by item type (e.g., TextBook, DVD)
    private List<String> sections; // Filter by section (e.g., General Reference)
    private List<String> collection; // Filter by collection (e.g., Books, Journals)

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchCriterion {
        private String idx; // Field name to filter on (e.g., "ti" for title, "au" for author)
        private String searchTerm; // The search term for the field
        private String operator; // Logical operator: AND, OR, NOT (optional)
    }
}
