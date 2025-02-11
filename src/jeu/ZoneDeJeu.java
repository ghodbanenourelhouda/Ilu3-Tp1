package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.*;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	private List<Borne> bornes; // faire les new ici
	private List<Bataille> bataille;
	private List<Limite> limites;
	private Set<Botte> bottes;

	public ZoneDeJeu() {
		this.bornes = new ArrayList<>();
		this.bataille = new ArrayList<>();
		this.limites = new ArrayList<>();
		this.bottes = new HashSet<>();

	}
	

	public Set<Botte> getBottes() {
		return bottes;
	}
	
	public int donnerLimitationVitesse() {
		if (estPrioritaire()) {
			return 200;
		}

		if (limites.isEmpty()) {
			return 200;
		}

		Limite derniereLimite = limites.get(limites.size() - 1);
		if (derniereLimite instanceof FinLimite) {
			return 200;
		}

		return 50;
	}

	public int donnerKmParcourus() {
		int totalKm = 0;

		for (Borne borne : bornes) {
			totalKm += borne.getKm();
		}

		return totalKm;
	}

	public void deposer(Carte carte) {
		if (carte instanceof Borne borne) {
			bornes.add(borne);
		}

		if (carte instanceof FinLimite limites|| carte instanceof DebutLimite limite) { // mm chose que borne
			limites.add(0,(Limite) carte);
		}

		if (carte instanceof Bataille bataille) {
			bataille.add(0,(Bataille) carte);
		}

		if (carte instanceof Botte) {
			bottes.add(0,(Botte) carte);
		}
	}

//	public boolean peutAvancer() {
//	    if (!bataille.isEmpty() && bataille.get(0).equals(Cartes.FEU_VERT)) {
//	        return true;
//	    }
//	    return false;
//	}

	public boolean peutAvancer() {

		if (bataille.isEmpty() && estPrioritaire()) {
			return true;
		}

		if (!bataille.isEmpty()) {
			Bataille sommet = bataille.get(0);
			if (sommet.equals(Cartes.FEU_VERT)) {
				return true;
			}

			if (sommet.equals(Cartes.FEU_VERT)) {
				return true;
			}

			if (sommet instanceof Parade && estPrioritaire()) {
				return true;
			}

			if (sommet instanceof Attaque && ((Attaque) sommet).getType() == Type.FEU && estPrioritaire()) {
				return true;
			}

			if (sommet instanceof Attaque && estPrioritaire()) {
				Type typeAttaque = ((Attaque) sommet).getType();
				for (Botte botte : bottes) {
					if (botte.getType() == typeAttaque) {
						return true;
					}
				}
			}

		}

		return false;
	}

//	private boolean estDepotFeuVertAutorise() {
//		if (bataille.isEmpty()) {
//			return true;
//		}
//
//		if (bataille.get(0).equals(Cartes.FEU_ROUGE)) {
//			return true;
//		}
//
//		if (!bataille.get(0).equals(Cartes.FEU_VERT)) {
//			return true;
//		}
//
//		return false;
//	}

	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) {
			return false;
		}

		if (bataille.isEmpty()) {
			return true;
		}

		Bataille sommet = bataille.get(0);

		if (sommet.equals(Cartes.FEU_ROUGE)) {
			return true;
		}

		if (sommet instanceof Parade && !sommet.equals(Cartes.FEU_VERT)) {
			return true;
		}

		if (sommet instanceof Attaque && aBotteCorrespondante((Attaque) sommet)) {
			return true;
		}

		return false;

	}
	
	//fonction Aux
	boolean aBotteCorrespondante(Attaque attaque) {
		String botte = attaque.getType().getBotte();
		return bottes.contains(botte);
	}

	boolean estDepotBorneAutorise(int borne) {

		int limitationVitesse = donnerLimitationVitesse();
		if (borne > limitationVitesse || donnerKmParcourus() + borne > 1000 ) {
			return false;
		}
		

		return true;

	}

	boolean estDepotLimiteAutorise(Limite limite) {
		if (estPrioritaire()) {
			return false;
		} 
		
		if (limites.isEmpty()) {
			return limite instanceof DebutLimite;
		}

		Limite derniereLimite = limites.get(0);

		if (limite instanceof DebutLimite) {
			return derniereLimite instanceof FinLimite;
		}

		if (limite instanceof FinLimite) {
			return derniereLimite instanceof DebutLimite;
		}

		return false;
	}
	
		
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
	        return peutAvancer(); 
		}
		
		if (bataille instanceof Parade) {

			if (bataille.equals(Cartes.FEU_VERT)) {
				if (this.bataille.isEmpty()) {
					return true;
				}

				Bataille derniereBataille = this.bataille.get(0);

				if (derniereBataille.equals(Cartes.FEU_ROUGE)) {
					return true;
				}

				if (derniereBataille instanceof Parade && !derniereBataille.equals(Cartes.FEU_VERT)) {
					return true;
				}
				return false; //TODO la parade a l'attaque mm type que le sommet
			}

			if (!this.bataille.isEmpty()) {
				Bataille derniereBataille = this.bataille.get(0);

				if (derniereBataille instanceof Attaque && derniereBataille.getType().equals(bataille.getType())) {
					return true;
				}
			}

			return false;
		}
		return false;
	}


	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne.getKm());
		}

		if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}

		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}

		if (carte instanceof Botte) {
			return !bottes.contains(carte);
		}

		return false;

	}

	public boolean estPrioritaire() {
		return bottes.contains(Cartes.PRIORITAIRE);
	}


	public Bataille getSommetBataille() {
		return bataille.get(0);
	}

}
