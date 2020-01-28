package dev.hotel.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@Service
public class ClientService {
	private ClientRepository clientRepository;

	/**
	 * @param clientRepository
	 */
	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	public List<Client> returnClients() {
		return this.clientRepository.findAll();
	}

	public List<Client> clientByName(@RequestParam("nom") String nom) {
		return this.clientRepository.findByNom(nom);

	}

	public UUID addClient(@RequestBody Client clientIn) {
		if (this.clientRepository.existsByNomAndPrenoms(clientIn.getNom(), clientIn.getPrenoms())) {
			throw new EntityExistsException();
		}
		return this.clientRepository.save(clientIn).getUuid();
	}

}
