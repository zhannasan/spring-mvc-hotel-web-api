package dev.hotel.exec;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

@Controller
public class InsererReservation implements Runnable {
	private static Logger LOG = LoggerFactory.getLogger(InsererReservation.class);
	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	/**
	 * @param reservationRepository
	 * @param clientRepository
	 * @param chambreRepository
	 */
	public InsererReservation(ReservationRepository reservationRepository,
			ClientRepository clientRepository, ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}


	@Override
	public void run() {
		try {
			List<Chambre> chambres = Arrays.asList(this.chambreRepository.findByNumero("22"),
					this.chambreRepository.findByNumero("21"));
			Client client = this.clientRepository.findByNomAndPrenoms("Krouac", "Jack");
			if (this.clientRepository.findByNomAndPrenoms("Kerouac", "Jack") == null) {
				this.clientRepository.save(client);
			}

			Reservation reservation = new Reservation();
			reservation.setChambres(chambres);
			reservation.setClient(client);
			reservation.setDateDebut(LocalDate.of(2019, 10, 22));
			reservation.setDateFin(LocalDate.of(2019, 11, 2));
			this.reservationRepository.save(reservation);
		} catch (EntityExistsException e) {
			LOG.error("No Hotel/Chambre/Client found");
		}
	}

}
