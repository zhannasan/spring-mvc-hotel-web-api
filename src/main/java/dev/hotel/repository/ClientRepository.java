package dev.hotel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.hotel.entite.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

	List<Client> findByNom(String nom);

	@Query("Select c FROM Client c where nom=?1 AND prenoms=?2")
	Client findByNomAndPrenoms(String nom, String prenoms);

}
