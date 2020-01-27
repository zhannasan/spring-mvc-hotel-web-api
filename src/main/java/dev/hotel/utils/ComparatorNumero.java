package dev.hotel.utils;

import java.util.Comparator;

import dev.hotel.entite.Chambre;

public class ComparatorNumero implements Comparator<Chambre> {

	@Override
	public int compare(Chambre p, Chambre q) {
		return q.getNumero().compareTo(p.getNumero());
	}

}
