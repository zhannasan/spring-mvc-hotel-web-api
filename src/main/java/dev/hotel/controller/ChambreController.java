package dev.hotel.controller;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.exec.InsererChambre;
import dev.hotel.repository.ChambreRepository;

@RestController
@RequestMapping("chambre")
public class ChambreController {
	private static Logger LOG = LoggerFactory.getLogger(InsererChambre.class);
	private ChambreRepository chambreRepository;
	
	
	/**
	 * @param chambreRepository
	 */
	public ChambreController(ChambreRepository chambreRepository) {
		super();
		this.chambreRepository = chambreRepository;
	}


	@RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	public List<Chambre> returnAllClientList() {
		try {
			List<Chambre> chambres = this.chambreRepository.findAll();
			return chambres;
		} catch (EntityNotFoundException e) {
			LOG.error("List chambres is empty.");
			return Collections.emptyList();
		}
	}


}
