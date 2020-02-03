package dev.hotel.controller;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.service.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	private ClientService clientService;

	/**
	 * @param clientService
	 */
	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping
	public List<Client> returnClients() {
		return this.clientService.returnClients();
	}

	@GetMapping(params = "nom")
	public List<Client> clientByName(@RequestParam("nom") String nom) {
		return this.clientService.clientByName(nom);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UUID addClient(@RequestBody @Valid Client clientIn) {
		return this.clientService.addClient(clientIn);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	
	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<String> clientPresent() {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The client with this name already exists.");
	}

}
