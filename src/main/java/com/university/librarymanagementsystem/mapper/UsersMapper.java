package com.university.librarymanagementsystem.mapper;

import com.university.librarymanagementsystem.dto.UsersDto;
import com.university.librarymanagementsystem.entity.Users;

public class UsersMapper {

    public static UsersDto mapToUsersDto(Users users) {
        return new UsersDto(
                users.getUserId(),
                users.getLibraryCardNumber(),
                users.getSchoolId(),
                users.getPassword(),
                users.getUserType());
    }
    
    public static Users mapToUsersDto(UsersDto usersDto) {
        return new Users(
            usersDto.getUserId(), 
            usersDto.getLibraryCardNumber(), 
            usersDto.getSchoolId(), 
            usersDto.getPassword(), 
            usersDto.getUserType()
        ); 
    }

}
