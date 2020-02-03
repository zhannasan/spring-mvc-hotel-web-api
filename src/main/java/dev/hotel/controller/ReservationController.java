package dev.hotel.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ReservationDTO;
import dev.hotel.entite.Reservation;
import dev.hotel.service.ReservationService;

@RestController
@RequestMapping("reservations")
public class ReservationController {
	private ReservationService reservationService;

	/**
	 * @param reservationService
	 */
	public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}

	@GetMapping
	public List<Reservation> returnReservations() {
		return this.reservationService.returnReservations();
	}



	@PostMapping(params = "reservation") // (consumes =
										// MediaType.APPLICATION_FORM_URLENCODED_VALUE,
					// produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Reservation> addReservation(@RequestParam("reservation") ReservationDTO reservationIn) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.reservationService.addReservation(reservationIn));
	}

	@ExceptionHandler(value = { EntityNotFoundException.class })
	public ResponseEntity<String> reservationPresent(EntityNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No entity found. " + exception.getMessage());
	}
}
