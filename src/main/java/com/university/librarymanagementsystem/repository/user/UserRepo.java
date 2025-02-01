package com.university.librarymanagementsystem.repository.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.user.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByLibraryCardNumber(String libraryCardNumber);

    @Query(value = """
            SELECT u.library_card_number AS libraryCardNumber,
                   d.dept_name AS departmentName
            FROM users u
            INNER JOIN stakeholders s ON u.school_id = s.id
            INNER JOIN departments d ON s.department = d.dept_id
            WHERE u.library_card_number = :libraryCardNumber
            """, nativeQuery = true)
    List<Object[]> findByLibraryCardNumberWithDepartment(String libraryCardNumber);

}
