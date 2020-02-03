package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.hotel.entite.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {


}
