package dev.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dev.hotel.dto.ReservationDTO;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@Service
public class ReservationService {
	private ReservationRepository reservationRepository;
	private ChambreRepository chambreRepository;
	private ClientRepository clientRepository;

	/**
	 * @param reservationRepository
	 * @param chambreRepository
	 * @param clientRepository
	 */
	public ReservationService(ReservationRepository reservationRepository, ChambreRepository chambreRepository,
			ClientRepository clientRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.chambreRepository = chambreRepository;
		this.clientRepository = clientRepository;
	}

	public Reservation addReservation(ReservationDTO reservationIn) {
		ModelMapper modelMapper = new ModelMapper();
		this.clientRepository.findById(reservationIn.getClientUuid())
				.orElseThrow(() -> new EntityExistsException("No client found."));
		reservationIn.getChambreUuid().stream()
			.map(chambreUuid -> chambreRepository.findById(chambreUuid)
				.orElseThrow(() -> new EntityExistsException("No chambre found.")))
				.collect(Collectors.toList());
		return this.reservationRepository.save(modelMapper.map(reservationIn, Reservation.class));
	}

	public List<Reservation> returnReservations() {
		return this.reservationRepository.findAll();
	}


}
