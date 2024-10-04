package com.university.librarymanagementsystem.service;

import java.util.List;

import com.university.librarymanagementsystem.dto.UsersDto;

public interface UsersService {

    UsersDto createUsers(UsersDto usersDto);

    UsersDto getUserById(Long userId);

    List<UsersDto> getAllUsers(); 
}
