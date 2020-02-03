package dev.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Chambre;
import dev.hotel.service.ChambreService;

@RestController
@RequestMapping("chambres")
public class ChambreController {
	private ChambreService chambreService;

	/**
	 * @param chambreService
	 */
	public ChambreController(ChambreService chambreService) {
		super();
		this.chambreService = chambreService;
	}

	@GetMapping
	public List<Chambre> returnChambres() {
		return this.chambreService.returnChambres();
	}

	@GetMapping(value = "available")
	public List<Chambre> existsNumeroNotLike() {
		return this.chambreService.existsNumeroNotLike();
	}
}
