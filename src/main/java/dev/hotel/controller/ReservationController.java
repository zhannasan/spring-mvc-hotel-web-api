package dev.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ReservationDTO;
import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.exec.InsererChambre;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@RestController
@RequestMapping("reservation")
public class ReservationController {
	private static Logger LOG = LoggerFactory.getLogger(InsererChambre.class);
	private ReservationRepository reservationRepository;
	private ChambreRepository chambreRepository;
	private ClientRepository clientRepository;
	/**
	 * @param reservationRepository
	 */
	public ReservationController(ReservationRepository reservationRepository) {
		super();
		this.reservationRepository = reservationRepository;
	}

	// @RequestMapping(method = RequestMethod.POST)
	// @ResponseBody
	public ResponseEntity<String> addReservation(@RequestBody LocalDate dateDebut, LocalDate dateFin, UUID clientId,
			List<UUID> chambresIds) {
		ModelMapper modelMapper = new ModelMapper();
		ReservationDTO orderDTO = modelMapper.map(reservationIn, ReservationDTO.class);
			List<Chambre> chambres = reservationIn.getChambres();
			for(int i=0; i<chambres.size();i++){
			if(this.chambreRepository.findById(chambres.get(i).getUuid())==null){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such chambre number found.");
			}
			}
			Client client = reservationIn.getClient();
		if (this.clientRepository.findById(client.getUuid()) == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such client found.");
			}

			Reservation reservation = new Reservation();
			reservation.setChambres(chambres);
			reservation.setClient(client);
			reservation.setDateDebut(reservationIn.getDateDebut());
			reservation.setDateFin(reservationIn.getDateFin());
			this.reservationRepository.save(reservation);
		return ResponseEntity.status(HttpStatus.CREATED).body("Reservation created.");
	}

	@RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	public List<Reservation> returnAllReservationList() {
		try {
			List<Reservation> reservations = this.reservationRepository.findAll();
			return reservations;

		} catch (EntityExistsException e) {
			LOG.error("No Hotel found");
			return null;
		}

	}
}
