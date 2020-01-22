package dev.hotel.exec;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Hotel;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.HotelRepository;

@Controller
public class InsererChambre implements Runnable {
	private static Logger LOG = LoggerFactory.getLogger(InsererChambre.class);
	private ChambreRepository chambreRepository;
	private HotelRepository hotelRepository;

	/**
	 * @param chambreRepository
	 * @param hotelRepository
	 */
	public InsererChambre(ChambreRepository chambreRepository, HotelRepository hotelRepository) {
		super();
		this.chambreRepository = chambreRepository;
		this.hotelRepository = hotelRepository;
	}


	@Override
	public void run() {
		try {
			Hotel hotel = this.hotelRepository.findByNom("Bordmer");
		
		
		List<Chambre> chambres = Arrays.asList(new Chambre("10", 12.5f, hotel), new Chambre("11", 12.5f, hotel),
				new Chambre("12", 12.5f, hotel), new Chambre("20", 16.5f, hotel), new Chambre("21", 16.5f, hotel),
				new Chambre("22", 16.5f, hotel));
		this.chambreRepository.saveAll(chambres);
		} catch (EntityExistsException e) {
			LOG.error("No Hotel found");
		}
	}

}
