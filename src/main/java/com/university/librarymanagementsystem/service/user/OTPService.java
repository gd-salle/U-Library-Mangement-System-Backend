package com.university.librarymanagementsystem.service.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OTPService {

    @Autowired
    private JavaMailSender mailSender;

    // Twilio Configuration (Use your Twilio credentials)
    private static final String TWILIO_PHONE_NUMBER = "+639093334396"; // Your Twilio phone number
    private static final String ACCOUNT_SID = "AC09be47a5701c996bea8d665d1daba7be";
    private static final String AUTH_TOKEN = "99a785bd755c8784ea75c1691b155e03";

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
        boolean result = otp.equals(storedOtp);
        return result;
    }

    public void sendEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        mailSender.send(message);
    }

    public void sendSMS(String to, String otp) {
        Message.creator(new PhoneNumber(to), new PhoneNumber(TWILIO_PHONE_NUMBER), "Your OTP code is: " + otp).create();
    }
}
