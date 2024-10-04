package com.university.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.university.librarymanagementsystem.entity.Users;


public interface UserRepository extends JpaRepository<Users, Long>{

}
