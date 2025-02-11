package testsFonctionnels;

import cartes.Attaque;
import cartes.JeuDeCartes;
import cartes.Type;

public class TestJeuDeCartes {

	public static void main(String[] args) {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        String affichage = jeuDeCartes.affichageJeuDeCartes();
        System.out.println(affichage);
        boolean estRespecter = jeuDeCartes.checkCount();
        System.out.println(estRespecter);
       
        
    }

}
