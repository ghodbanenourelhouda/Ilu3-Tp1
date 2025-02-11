package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	
	private List<Carte> cartes;  //TODO rendre la class itérable sur carte
	
	public MainJoueur() {
        this.cartes = new ArrayList<>();
    }
	
	 public List<Carte> getCartes() {
	        return cartes;  
	    }
	
	
	public void prendre (Carte carte) {
		cartes.add(carte);
	}
	
	public void jouer (Carte carte) {
		assert cartes.contains(carte) : "Erreur : la liste ne contiens pas cette carte" ;
		cartes.remove(carte);
	}
	
	@Override
    public String toString() {
        StringBuilder affichage = new StringBuilder("Main du joueur :\n");
        for (Carte carte : cartes) {
            affichage.append(carte.toString()).append(" , ");
        }
        affichage.append(" \n");
        return affichage.toString();
    }

	
}
