package com.university.librarymanagementsystem.service;

import com.university.librarymanagementsystem.dto.UsersDto;

public interface UsersService {

    UsersDto createUsers(UsersDto usersDto);

    UsersDto getUserById(Long userId);
}
