package dev.hotel.exec;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.hotel.entite.Hotel;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.HotelRepository;
import dev.hotel.repository.ReservationRepository;

public class InsererReservation implements Runnable {
	private static Logger LOG = LoggerFactory.getLogger(InsererReservation.class);
	private ReservationRepository reservationRepository;
	private HotelRepository hotelRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	/**
	 * @param reservationRepository
	 * @param hotelRepository
	 * @param clientRepository
	 * @param chambreRepository
	 */
	public InsererReservation(ReservationRepository reservationRepository, HotelRepository hotelRepository,
			ClientRepository clientRepository, ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.hotelRepository = hotelRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}


	@Override
	public void run() {
		try {
			Hotel hotel = this.hotelRepository.findByNom("Bordmer");
		// List<Chambre> chambres = this.chambreRepository.findAllByNumber();
		// List<Client> clients = this.clientRepository.findAll
		} catch (EntityExistsException e) {
			LOG.error("No Hotel/Chambre/Client found");
		}
	}

}
