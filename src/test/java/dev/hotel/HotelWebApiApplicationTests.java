package dev.hotel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import dev.hotel.dto.ReservationDTO;
import dev.hotel.entite.Reservation;

@SpringBootTest
class HotelWebApiApplicationTests {
	private static final ModelMapper modelMapper = new ModelMapper();
	@Test
	public void checkReserveMapping() {
		ReservationDTO creation = new ReservationDTO();
		creation.setChambreUuid(Arrays.asList(UUID.fromString("62dc4d63-6937-4231-a3da-76d97f69f809")));
		creation.setClientUuid(UUID.fromString("724afcce-840c-4c2b-a2da-c2d2c718b481"));
		creation.setDateDebut(LocalDate.of(2019, 4, 5));
		creation.setDateFin(LocalDate.of(2019, 4, 15));

		Reservation res = modelMapper.map(creation, Reservation.class);
		assertEquals(creation.getClientUuid().toString(), res.getClient().getUuid().toString());
		assertEquals(creation.getDateDebut().toString(), res.getDateDebut().toString());
		assertEquals(creation.getDateFin().toString(), res.getDateFin().toString());
		assertEquals(res.getChambres().get(0).getUuid().toString(), creation.getChambreUuid().get(0).toString());
	}

}
