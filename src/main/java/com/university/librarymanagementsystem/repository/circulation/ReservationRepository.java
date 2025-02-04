package com.university.librarymanagementsystem.repository.circulation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.university.librarymanagementsystem.entity.circulation.Reservation;
import com.university.librarymanagementsystem.entity.user.Users;
import com.university.librarymanagementsystem.enums.ReservationStatus;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    int countByUserUserIdAndReservationStatus(Long userId, ReservationStatus status);
}
