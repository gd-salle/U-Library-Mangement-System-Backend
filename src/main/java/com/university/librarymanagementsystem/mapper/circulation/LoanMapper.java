package com.university.librarymanagementsystem.mapper.circulation;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.university.librarymanagementsystem.dto.circulation.LoanDto;

public class LoanMapper {

    // Map raw data to LoanDto (though this is not strictly necessary in your case)
    public static LoanDto toLoanDto(Object[] result) {
        // Assuming the result is ordered as per the query
        return new LoanDto(
                (Long) result[0], // loanId
                (String) result[1], // accessionNo
                (String) result[2], // barcode
                (String) result[3], // title
                (String) result[4], // callNumber
                (String) result[5], // authorName
                (String) result[6], // borrowerLibraryCardNo
                (String) result[7], // departmentName
                convertTimestampToLocalDateTime(result[8]), // borrowDate
                convertTimestampToLocalDateTime(result[9]), // returnDate
                convertTimestampToLocalDateTime(result[10]), // due
                (String) result[11] // status
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
