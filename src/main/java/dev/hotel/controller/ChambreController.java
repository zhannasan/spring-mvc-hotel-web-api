package dev.hotel.controller;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.repository.ChambreRepository;

@RestController
@RequestMapping("chambre")
public class ChambreController {
	private static Logger LOG = LoggerFactory.getLogger(ChambreController.class);
	private ChambreRepository chambreRepository;
	
	
	/**
	 * @param chambreRepository
	 */
	public ChambreController(ChambreRepository chambreRepository) {
		super();
		this.chambreRepository = chambreRepository;
	}


	@GetMapping(path = "all")
	@ResponseBody
	public List<Chambre> returnAllClientList() {
		try {
			List<Chambre> chambres = this.chambreRepository.findAll();
			// List<Chambre> sortedChambre = Collections.sort(chambres, new
			// ComparatorNumero());
			return chambres;
		} catch (EntityNotFoundException e) {
			LOG.error("List chambres is empty.");
			return Collections.emptyList();
		}
	}


}
