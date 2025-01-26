package com.university.librarymanagementsystem.controller.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.university.librarymanagementsystem.dto.user.StakeholdersDto;
import com.university.librarymanagementsystem.service.user.OTPService;
import com.university.librarymanagementsystem.service.user.StakeHolderService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
public class StakeHolderController {

    @Autowired
    private StakeHolderService stakeHolderService;
    @Autowired
    public OTPService otpService;

    // Build Get Stakeholder REST API
    @GetMapping("/verify/{stakeHolderId}")
    public ResponseEntity<StakeholdersDto> getStakeholderId(@PathVariable("stakeHolderId") String id) {

        StakeholdersDto stakeholdersDto = stakeHolderService.getStudentWithDepartmentAndCourse(id);

        String otp = otpService.generateOTP();

        // Store OTP with stakeholder email or phone number
        otpService.storeOTP(stakeholdersDto.getEmailAdd(), otp);

        // Send OTP via email and/or SMS
        otpService.sendEmail(stakeholdersDto.getEmailAdd(), otp);

        return ResponseEntity.ok(stakeholdersDto);
    }

    // Build API to verify OTP
    @PostMapping("/verify/confirm-otp")
    public ResponseEntity<Map<String, String>> confirmOtp(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("emailAdd");
        String otp = requestBody.get("otp");

        boolean isVerified = otpService.verifyOTP(email, otp);
        Map<String, String> response = new HashMap<>();
        if (isVerified) {
            response.put("success", "true");
            response.put("message", "OTP verified successfully!");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", "false");
            response.put("message", "Invalid or expired OTP!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}