package com.university.librarymanagementsystem.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.university.librarymanagementsystem.dto.LoanDto;

public class LoanMapper {

    // Map raw data to LoanDto (though this is not strictly necessary in your case)
    public static LoanDto toLoanDto(Object[] result) {
        // Assuming the result is ordered as per the query
        return new LoanDto(
                (Long) result[0], // loanId
                (String) result[1], // title
                (String) result[2], // callNumber
                (String) result[3], // authorName
                (String) result[4], // borrowerFullName
                (String) result[5], // departmentName
                (String) result[6], // borrowerId
                convertTimestampToLocalDateTime(result[7]), // borrowDate
                convertTimestampToLocalDateTime(result[8]), // returnDate
                (String) result[9] // status
        );
    }

    // Helper method to convert Timestamp to LocalDateTime
    private static LocalDateTime convertTimestampToLocalDateTime(Object timestampObj) {
        if (timestampObj == null) {
            return null; // Handle null values gracefully
        }

        // Ensure the object is of type Timestamp
        if (timestampObj instanceof Timestamp) {
            return ((Timestamp) timestampObj).toLocalDateTime();
        }

        return null; // Return null if the object is not a Timestamp (fallback)
    }
}
