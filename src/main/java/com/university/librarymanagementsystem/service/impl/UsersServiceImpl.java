package com.university.librarymanagementsystem.service.impl;

import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.UsersDto;
import com.university.librarymanagementsystem.entity.Users;
import com.university.librarymanagementsystem.exception.ResourceNotFoundException;
import com.university.librarymanagementsystem.mapper.UsersMapper;
import com.university.librarymanagementsystem.repository.UserRepository;
import com.university.librarymanagementsystem.service.UsersService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UserRepository userRepository;

    @Override
    public UsersDto createUsers(UsersDto usersDto) {

        Users user = UsersMapper.mapToUsersDto(usersDto);
        Users savedUser = userRepository.save(user);
        return UsersMapper.mapToUsersDto(savedUser);
    }

    @Override
    public UsersDto getUserById(Long userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with given id: " + userId));
        return UsersMapper.mapToUsersDto(user);
    }

}
