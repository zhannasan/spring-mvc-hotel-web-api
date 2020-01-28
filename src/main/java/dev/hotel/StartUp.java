package dev.hotel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Hotel;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.HotelRepository;
import dev.hotel.repository.ReservationRepository;

@Component
public class StartUp {
	private static final Logger LOG = LoggerFactory.getLogger(StartUp.class);

	private HotelRepository hotelRepository;
	private ChambreRepository chambreRepository;
	private ClientRepository clientRepository;
	private ReservationRepository reservationRepository;

	/**
	 * @param hotelRepository
	 * @param chambreRepository
	 * @param clientRepository
	 * @param reservationRepository
	 */
	public StartUp(HotelRepository hotelRepository, ChambreRepository chambreRepository,
			ClientRepository clientRepository, ReservationRepository reservationRepository) {
		super();
		this.hotelRepository = hotelRepository;
		this.chambreRepository = chambreRepository;
		this.clientRepository = clientRepository;
		this.reservationRepository = reservationRepository;
	}



	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		LOG.info("Démarrage de l'application");
		if (this.hotelRepository.count() == 0) {
			Hotel hotel = new Hotel("Bordmer", 3);
			this.hotelRepository.save(hotel);

			if (this.chambreRepository.count() == 0) {
				List<Chambre> chambres = Arrays.asList(new Chambre("10", 12.5f, hotel), new Chambre("11", 12.5f, hotel),
						new Chambre("12", 12.5f, hotel), new Chambre("20", 16.5f, hotel),
						new Chambre("21", 16.5f, hotel), new Chambre("22", 16.5f, hotel));
				this.chambreRepository.saveAll(chambres);
			}
		}
		if (this.clientRepository.count() == 0) {
			List<Client> clients = Arrays.asList(new Client("Pierre", "Jean"), new Client("Pierre", "Melissa"),
					new Client("Kerouac", "Jack"), new Client("Wataya", "Risa"), new Client("Balano", "Roberto"),
					new Client("Burroughs", "William"), new Client("Pamuk", "Orhan"), new Client("Bukowski", "Charles"),
					new Client("Jonasson", "Jonas"));
			this.clientRepository.saveAll(clients);
		}
		if (this.reservationRepository.count() == 0) {
			List<Chambre> chambres = Arrays.asList(this.chambreRepository.findByNumero("22"),
					this.chambreRepository.findByNumero("21"));
			Client client = this.clientRepository.findByNomAndPrenoms("Kerouac", "Jack");
			Reservation reservation = new Reservation();
			reservation.setChambres(chambres);
			reservation.setClient(client);
			reservation.setDateDebut(LocalDate.of(2019, 10, 22));
			reservation.setDateFin(LocalDate.of(2019, 11, 2));
			this.reservationRepository.save(reservation);
		}
	}

}
