package com.university.librarymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.UsersDto;
import com.university.librarymanagementsystem.service.UsersService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UsersService usersService;

    // Build Add User Rest API
    @PostMapping
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto usersDto) {
        UsersDto savedUser = usersService.createUsers(usersDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build Get User Rest API
    @GetMapping("{id}")
    public ResponseEntity<UsersDto> getUser(@PathVariable("id") Long userId) {
        UsersDto userDto = usersService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

}
