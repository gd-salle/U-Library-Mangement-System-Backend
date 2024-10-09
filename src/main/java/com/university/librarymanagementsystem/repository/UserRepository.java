package com.university.librarymanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.librarymanagementsystem.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    boolean existsByLibraryCardNumber(String libraryCardNumber);

    Optional<Users> findByLibraryCardNumber(String libraryCardNumber);

}
