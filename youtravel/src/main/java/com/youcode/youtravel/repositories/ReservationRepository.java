package com.youcode.youtravel.repositories;

import com.youcode.youtravel.entities.Reservation;
import com.youcode.youtravel.utils.ReservationID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, ReservationID> {
}
