package com.university.librarymanagementsystem.service.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    @Autowired
    private JavaMailSender mailSender;

    Random random = new Random();
    private Map<String, String> otpStorage = new HashMap<>();

    // Generates OTP and stores it in the map with the associated email or phone
    // number
    public String generateOTP() {
        int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

    public void storeOTP(String key, String otp) {
        otpStorage.put(key, otp); // Store OTP associated with a key (email or phone)
    }

    public boolean verifyOTP(String key, String otp) {
        String storedOtp = otpStorage.get(key); // Retrieve the OTP associated with the key (email)
        if (storedOtp == null) {
            return false; // No OTP stored for this email
        }
        return otp.equals(storedOtp);
    }

    public void sendEmail(String emailAdd, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAdd);
        message.setSubject("University Library Management System - OTP Verification");
        message.setText(
                "Dear User,\n\n" +
                        "Here is your One-Time Password (OTP) for secure access to your account:\n\n" +
                        "OTP: " + otp + "\n\n" +
                        "Please enter this code to ACTIVATE your account. This OTP is valid for a limited time for security purposes.\n\n"
                        +
                        "If you did not request this OTP, please contact our support team immediately.\n\n" +
                        "Best regards,\n" +
                        "University Library Management System\n" +
                        "University of Nueva Caceres\n\n" +
                        "Note: This email is sent from a no-reply address. Do not reply to this email. For support, please visit our website or call our support team.\n\n"
                        +
                        "You received this email because you are activating your account with University Library Management System at University of Nueva Caceres.");

        mailSender.send(message);
    }

    // public void sendSMS(String to, String otp) {
    // Message.creator(new PhoneNumber(to), new PhoneNumber(TWILIO_PHONE_NUMBER),
    // "Your OTP code is: " + otp).create();
    // }
}
