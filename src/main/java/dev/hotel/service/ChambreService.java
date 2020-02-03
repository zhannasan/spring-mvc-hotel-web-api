package dev.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;

@Service
public class ChambreService {
	private ChambreRepository chambreRepository;

	/**
	 * @param chambreRepository
	 */
	public ChambreService(ChambreRepository chambreRepository) {
		super();
		this.chambreRepository = chambreRepository;
	}

	public List<Chambre> returnChambres() {
		return this.chambreRepository.findByOrderByNumeroAsc();
	}

	public List<Chambre> existsNumeroNotLike() {
		return this.chambreRepository.existsNumeroNotLike();
	}

	public List<Chambre> returnChambresByDate(String dateDebut, String dateFin) {
		return this.chambreRepository.findChambreByDateDebutFin(dateDebut, dateFin);

	}
}
