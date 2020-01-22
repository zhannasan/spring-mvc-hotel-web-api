package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.hotel.entite.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, UUID> {

	Chambre findByNumero(String numero);

}
