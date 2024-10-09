package com.university.librarymanagementsystem.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
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

        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 12, 3);
        usersDto.setPassword(encoder.encode(usersDto.getPassword()));

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

    @Override
    public List<UsersDto> getAllUsers() {

        List<Users> users = userRepository.findAll();
        return users.stream().map(UsersMapper::mapToUsersDto).collect(Collectors.toList());
    }

    @Override
    public boolean doesLibraryCardNumberExist(String libraryCardNumber) {
        return userRepository.existsByLibraryCardNumber(libraryCardNumber);
    }

    @Override
    public UsersDto updateUser(Long userId, UsersDto updatedUser) {

        Users user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Not found"));

        user.setLibraryCardNumber(updatedUser.getLibraryCardNumber());
        user.setPassword(updatedUser.getPassword());
        user.setSchoolId(updatedUser.getSchoolId());
        user.setUserType(updatedUser.getUserType());

        Users updatedUserObj = userRepository.save(user);
        return UsersMapper.mapToUsersDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {

        @SuppressWarnings("unused")
        Users user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Not found"));

        userRepository.deleteById(userId);

    }

    @Override
    public UsersDto login(String libraryCardNumber, String password) {

        // Fetch the user by libraryCardNumber
        Users user = userRepository.findByLibraryCardNumber(libraryCardNumber).orElseThrow(
                () -> new ResourceNotFoundException("User not found with library card number: " + libraryCardNumber));

        // Create an instance of BCryptPasswordEncoder to compare the passwords
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 12, 3);

        // Check if the provided password matches the encoded password in the database
        if (!encoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Return the user if the password matches
        return UsersMapper.mapToUsersDto(user);
    }

}
