package dev.hotel.entite;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Client extends BaseEntite {
	@NotEmpty
	@Size(min = 2, max = 50)
    private String nom;
	@NotEmpty
	@Size(min = 2)
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
