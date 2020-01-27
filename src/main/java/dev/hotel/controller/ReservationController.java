package dev.hotel.controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ReservationDTO;
import dev.hotel.entite.Chambre;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@RestController
@RequestMapping("reservation")
public class ReservationController {
	private static Logger LOG = LoggerFactory.getLogger(ReservationController.class);

	private ReservationRepository reservationRepository;
	private ChambreRepository chambreRepository;
	private ClientRepository clientRepository;

	/**
	 * @param reservationRepository
	 * @param chambreRepository
	 * @param clientRepository
	 */
	public ReservationController(ReservationRepository reservationRepository, ChambreRepository chambreRepository,
			ClientRepository clientRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.chambreRepository = chambreRepository;
		this.clientRepository = clientRepository;
	}

	@PostMapping(path = "creer")
	// @ResponseBody
	public ResponseEntity<String> addReservation(@RequestBody ReservationDTO reservationIn) {
		try {
			ModelMapper modelMapper = new ModelMapper();

			UUID clientId = reservationIn.getClientUuid();
			LOG.info(reservationIn.getClientUuid().toString());
			if (this.clientRepository.findById(clientId) == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such client found.");
			}

			List<UUID> chambresId = reservationIn.getChambreUuid();
			for (UUID u : chambresId) {
				LOG.info(u.toString());
			}

			List<Chambre> ch = this.chambreRepository.findAllById(chambresId);
			if (ch == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such chambre found.");
			}

			Reservation res = modelMapper.map(reservationIn, Reservation.class);
			this.reservationRepository.save(res);

			return ResponseEntity.status(HttpStatus.CREATED).body("Reservation" + res + " created.");

		} catch (EntityExistsException e) {
			LOG.error("No entity found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No entity found.");
			}
	}

	@GetMapping
	// @ResponseBody
	public List<Reservation> returnAllReservationList() {
		try {
			List<Reservation> reservations = this.reservationRepository.findAll();
			return reservations;

		} catch (EntityExistsException e) {
			LOG.error("No Hotel found");
			return Collections.emptyList();
		}

	}
}
