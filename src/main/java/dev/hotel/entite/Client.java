package dev.hotel.entite;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Client extends BaseEntite {

	@Column(unique = false)
    private String nom;
	@Column(unique = false)
    private String prenoms;

    public Client() {
    }

    public Client(String nom, String prenoms) {
        this.nom = nom;
        this.prenoms = prenoms;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

	@Override
	public String toString() {

		return this.nom + " " + this.prenoms;
	}
}
