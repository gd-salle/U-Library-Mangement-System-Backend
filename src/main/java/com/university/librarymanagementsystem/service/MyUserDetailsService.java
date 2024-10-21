package com.university.librarymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.university.librarymanagementsystem.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String libraryCardNumber) throws UsernameNotFoundException {
        return userRepo.findByLibraryCardNumber(libraryCardNumber).orElseThrow();
    }

}
