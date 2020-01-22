package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

@RestController
@RequestMapping("client")
public class ClientController {
	private static Logger LOG = LoggerFactory.getLogger(ClientController.class);
	private ClientRepository clientRepository;

	/**
	 * @param clientRepository
	 */
	public ClientController(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	// @ResponseBody
	public List<Client> returnAllClientList() {
		List<Client> clients = new ArrayList();
		if (!this.clientRepository.findAll().isEmpty()) {
			clients = this.clientRepository.findAll();
		}
		return clients;
	}
	// GET /clients
	@RequestMapping(method = RequestMethod.GET, params = "nom")
	// @ResponseBody
	public List<Client> returnClientByName(@RequestParam("nom") String nom) {
		List<Client> clients = new ArrayList();
		if (!this.clientRepository.findByNom(nom).isEmpty()) {
			clients = this.clientRepository.findByNom(nom);
			for(Client c : clients){
				c.setNom(c.getNom().toUpperCase());
			}
		}
		return clients;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	public ResponseEntity<String> addClient(@RequestBody Client clientIn) {
		try {
			Client client = this.clientRepository.findByNomAndPrenoms(clientIn.getNom(), clientIn.getPrenoms());
			if (client == null) {
				client = new Client(clientIn.getNom(), clientIn.getPrenoms());
				this.clientRepository.save(client);


			return ResponseEntity.status(HttpStatus.CREATED)
					.body("The client " + client.toString() + " has been successfully added.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("The client " + clientIn.getNom() + " " + clientIn.getPrenoms() + " already exists.");
			}
		} catch (EntityExistsException e) {
			LOG.error("No client found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("No client found.");
		}
	}

}
