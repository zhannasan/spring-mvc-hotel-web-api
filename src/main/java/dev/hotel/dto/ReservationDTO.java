package dev.hotel.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import dev.hotel.entite.BaseEntite;

public class ReservationDTO extends BaseEntite {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private UUID clientUuid;
	private List<UUID> chambreUuid;

	public ReservationDTO() {
		super();
	}
	
	/**
	 * @param dateDebut
	 * @param dateFin
	 * @param clientUuid
	 * @param chambreUuid
	 */
	public ReservationDTO(LocalDate dateDebut, LocalDate dateFin, UUID clientUuid, List<UUID> chambreUuid) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.clientUuid = clientUuid;
		this.chambreUuid = chambreUuid;
	}

	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut
	 *            the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin
	 *            the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the clientUuid
	 */
	public UUID getClientUuid() {
		return clientUuid;
	}

	/**
	 * @param clientUuid
	 *            the clientUuid to set
	 */
	public void setClientUuid(UUID clientUuid) {
		this.clientUuid = clientUuid;
	}

	/**
	 * @return the chambreUiid
	 */
	public List<UUID> getChambreUuid() {
		return chambreUuid;
	}

	/**
	 * @param chambreUuid
	 *            the chambreUuid to set
	 */
	public void setChambreUuid(List<UUID> chambreUuid) {
		this.chambreUuid = chambreUuid;
	}

}
