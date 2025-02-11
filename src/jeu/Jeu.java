package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot ;
	private List<Joueur> joueurs;
	private static final int NBCARTES = 6;
	
	public Jeu(JeuDeCartes jeuDeCarte) {
		Carte [] cartes = jeuDeCarte.donnerCartes();
		List<Carte> listeCartes = Arrays.asList(cartes);
		List<Carte> cartesMelangees = GestionCartes.melanger(listeCartes);
		sabot = new Sabot (cartesMelangees.toArray(new Carte[0]));   
		joueurs = new ArrayList<>();
	}
	
	public void inscrire(Joueur... nouveauxJoueurs) {
        joueurs.addAll(Arrays.asList(nouveauxJoueurs)); 
    }
	
	public void distribuerCartes() {
        for (int i = 0; i < NBCARTES; i++) {
            for (Joueur joueur : joueurs) {
                if (!sabot.estVide()) { 
                    Carte carte = sabot.piocher(); 
                    if (carte != null) {
                        joueur.getMain().prendre(carte);
                    }
                }
            }
        }
    }
	
	
	
	
}
