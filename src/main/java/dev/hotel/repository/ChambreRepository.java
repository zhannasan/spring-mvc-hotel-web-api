package dev.hotel.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.hotel.entite.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, UUID> {

	Chambre findByNumero(String numero);
	List<Chambre> findByOrderByNumeroAsc();

	@Query(value = "SELECT c.numero FROM Chambre c WHERE NOT EXISTS(SELECT r.chambre_uuid FROM Reservation_chambre r WHERE c.uuid = r.chambre_uuid)", nativeQuery = true)
	List<Chambre> existsNumeroNotLike();

	@Query(value = "Select chambre.* from ((reservation r left join reservation_chambre rc ON r.uuid=rc.reservation_uuid) inner join chambre on rc.chambre_uuid=chambre.uuid) where date_debut=?1 and date_fin=?2 ", nativeQuery = true)
	List<Chambre> findChambreByDateDebutFin(String dateDebut, String dateFin);


}
