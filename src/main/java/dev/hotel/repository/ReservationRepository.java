package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.hotel.entite.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

	/*
	 * @Query(value = "SELECT v.Date FROM Reservation v WHERE NOT EXISTS(SELECT
	 * r.chambre_uuid FROM Reservation_chambre r WHERE c.uuid =
	 * r.chambre_uuid)", nativeQuery = true) List<String> existsNumeroNotLike();
	 */
}
