package dev.hotel.exec;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Controller
public class InsererClients implements Runnable {
	private ClientRepository clientRepository;

	/**
	 * @param clientRepository
	 */
	public InsererClients(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@Override
	public void run() {
		List<Client> clients = Arrays.asList(new Client("Pierre", "Jean"), new Client("Pierre", "Melissa"),
				new Client("Kerouac", "Jack"), new Client("Wataya", "Risa"), new Client("Balano", "Roberto"),
				new Client("Burroughs", "William"), new Client("Pamuk", "Orhan"), new Client("Bukowski", "Charles"),
				new Client("Jonasson", "Jonas"));
		this.clientRepository.saveAll(clients);
	}

}
