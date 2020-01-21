package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.hotel.entite.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
	Hotel findByNom(String nom);
}
