package com.university.librarymanagementsystem.service.user;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.dto.circulation.BorrowerDetailsDto;
import com.university.librarymanagementsystem.dto.user.ReqRes;
import com.university.librarymanagementsystem.entity.user.Users;
import com.university.librarymanagementsystem.repository.user.UserRepo;

@Service
public class UserManagementService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();
        try {
            System.out.println("Attempting to register user with data: " + registrationRequest); // Log the request
                                                                                                 // details

            Users ourUser = new Users();
            ourUser.setLibraryCardNumber(registrationRequest.getLibraryCardNumber());
            ourUser.setSchoolId(registrationRequest.getSchoolId());
            ourUser.setRole(registrationRequest.getRole());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

            Users savedUser = userRepo.save(ourUser); // Save to database
            if (savedUser.getUserId() > 0) {
                resp.setUsers(savedUser);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            } else {
                resp.setMessage("User not saved");
                resp.setStatusCode(500);
            }
        } catch (Exception e) {
            System.err.println("Error while saving user: " + e.getMessage()); // Log error details
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLibraryCardNumber(),
                            loginRequest.getPassword()));
            var user = userRepo.findByLibraryCardNumber(loginRequest.getLibraryCardNumber()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest) {
        ReqRes response = new ReqRes();
        try {
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            Users users = userRepo.findByLibraryCardNumber(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

}
