package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.Limite;

public class Coup {
	private Joueur joueurCourant;
	private Carte carteJouee ; 
	private Joueur joueurCible;
	
	
	public Coup(Joueur joueurCourant, Carte carteJouee, Joueur joueurCible) {
        this.joueurCourant = joueurCourant;
        this.carteJouee = carteJouee;
        this.joueurCible = joueurCible;
    }
	
	
	public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public Carte getCarteJouee() {
        return carteJouee;
    }

   
    public Joueur getJoueurCible() {
        return joueurCible;
    }
    

    public boolean estValide () {
    	if (carteJouee instanceof Attaque || carteJouee instanceof Limite) {
            if (joueurCible != null && !joueurCible.equals(joueurCourant)) {
                return true;
            } else {
                return false; 
            }
        } else {
            return true; 
        }
    }
    
    @Override
    public String toString() {
        if (joueurCible == null) {
            return "défausse la carte " + carteJouee;
        } else {
            return "dépose la carte " + carteJouee + " dans la zone de jeu de " + joueurCible.getNom();
        }
    }

    
    
    
    
    
}
