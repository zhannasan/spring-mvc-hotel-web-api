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
	private UUID clientUiid;
	private List<UUID> chambreUiid;

	public ReservationDTO() {
		super();
	}
	/**
	 * @param dateDebut
	 * @param dateFin
	 * @param clientUiid
	 * @param chambreUiid
	 */
	public ReservationDTO(LocalDate dateDebut, LocalDate dateFin, UUID clientUiid, List<UUID> chambreUiid) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.clientUiid = clientUiid;
		this.chambreUiid = chambreUiid;
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
	 * @return the clientUiid
	 */
	public UUID getClientUiid() {
		return clientUiid;
	}

	/**
	 * @param clientUiid
	 *            the clientUiid to set
	 */
	public void setClientUiid(UUID clientUiid) {
		this.clientUiid = clientUiid;
	}

	/**
	 * @return the chambreUiid
	 */
	public List<UUID> getChambreUiid() {
		return chambreUiid;
	}

	/**
	 * @param chambreUiid
	 *            the chambreUiid to set
	 */
	public void setChambreUiid(List<UUID> chambreUiid) {
		this.chambreUiid = chambreUiid;
	}

}
