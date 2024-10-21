package com.university.librarymanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.librarymanagementsystem.entity.Users;

public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByLibraryCardNumber(String libraryCardNumber);
    
}
