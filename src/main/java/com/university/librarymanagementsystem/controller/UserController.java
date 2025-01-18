package com.university.librarymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.ReqRes;
import com.university.librarymanagementsystem.service.UserManagementService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class UserController {

    @Autowired
    private UserManagementService usersManagementService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
        System.out.println("Received registration request: " + reg); // Log incoming request
        ReqRes response = usersManagementService.register(reg);

        if (response.getStatusCode() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

}
