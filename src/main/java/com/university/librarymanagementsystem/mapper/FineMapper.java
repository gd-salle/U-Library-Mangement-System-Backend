package com.university.librarymanagementsystem.mapper;

import java.time.LocalDateTime;

import com.university.librarymanagementsystem.dto.FineDto;
import com.university.librarymanagementsystem.entity.Fine;
import com.university.librarymanagementsystem.entity.Users;

public class FineMapper {

    // Convert Fine entity to FineDTO
    public static FineDto toDTO(Fine fine) {
        FineDto fineDTO = new FineDto();
        fineDTO.setFineId(fine.getFineId());
        fineDTO.setLoanId(fine.getLoanId());
        fineDTO.setUserId(fine.getUser().getUserId());
        fineDTO.setFineDate(fine.getFineDate());
        fineDTO.setFineAmount(fine.getFineAmount());
        return fineDTO;
    }

    // Convert FineDTO to Fine entity
    public static Fine toEntity(FineDto fineDTO) {
        Fine fine = new Fine();
        fine.setFineId(fineDTO.getFineId());
        fine.setLoanId(fineDTO.getLoanId());

        // Set the user (Assumes you already have a user object available)
        Users user = new Users();
        user.setUserId(fineDTO.getUserId());
        fine.setUser(user);

        fine.setFineDate(fineDTO.getFineDate());
        fine.setFineAmount(fineDTO.getFineAmount());
        return fine;
    }
}
