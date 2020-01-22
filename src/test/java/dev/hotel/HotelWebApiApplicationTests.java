package dev.hotel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import dev.hotel.dto.ReservationDTO;

@SpringBootTest
class HotelWebApiApplicationTests {
	private static final ModelMapper modelMapper = new ModelMapper();
	@Test
	public void checkReserveMapping() {
		ReservationDTO reserv = new ReservationDTO();
		reserv.setChambreUiid(Arrays.asList(UUID.fromString("62dc4d63-6937-4231-a3da-76d97f69f809")));
		reserv.setClientUiid(UUID.fromString("724afcce-840c-4c2b-a2da-c2d2c718b481"));
		reserv.setDateDebut(LocalDate.of(2019, 4, 5));
		reserv.setDateFin(LocalDate.of(2019, 4, 15));
	}

}
