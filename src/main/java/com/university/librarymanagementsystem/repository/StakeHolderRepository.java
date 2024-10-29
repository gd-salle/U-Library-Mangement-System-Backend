package com.university.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.StakeHolders;
import java.util.List;
import java.util.Optional;

public interface StakeHolderRepository extends JpaRepository<StakeHolders, String> {

}
