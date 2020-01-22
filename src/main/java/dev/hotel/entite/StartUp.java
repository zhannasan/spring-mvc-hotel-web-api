package dev.hotel.entite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.hotel.repository.HotelRepository;

@Component
public class StartUp {
	private static final Logger LOG = LoggerFactory.getLogger(StartUp.class);

	private HotelRepository hotelRepository;

	/**
	 * @param hotelRepository
	 */
	public StartUp(HotelRepository hotelRepository) {
		super();
		this.hotelRepository = hotelRepository;
	}


	@EventListener(ContextRefreshedEvent.class)
	public void init() {

		LOG.info("Démarrage de l'application");

		if (this.hotelRepository.count() == 0) {

			Hotel hotel = new Hotel();
			hotel.setNom("Bordmer");
			hotel.setNombreEtoiles(3);

			this.hotelRepository.save(hotel);
		}
	}
}
