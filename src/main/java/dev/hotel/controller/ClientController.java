package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.exec.InsererChambre;
import dev.hotel.repository.ClientRepository;

@RestController
@RequestMapping("client")
public class ClientController {
	private static Logger LOG = LoggerFactory.getLogger(InsererChambre.class);
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
	@ResponseBody
	public List<Client> returnClientList(@RequestParam("nom") String nom) {
		List<Client> clients = new ArrayList();
		if (!this.clientRepository.findByNom(nom).isEmpty()) {
			clients = this.clientRepository.findByNom(nom);
			for(Client c : clients){
				c.setNom(c.getNom().toUpperCase());
			}
		}
		return clients;
	}

}
