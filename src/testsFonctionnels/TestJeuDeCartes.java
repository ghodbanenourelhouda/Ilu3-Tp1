package testsFonctionnels;

import cartes.JeuDeCartes;

public class TestJeuDeCartes {

	public static void main(String[] args) {
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        String affichage = jeuDeCartes.affichageJeuDeCartes();
        System.out.println(affichage);
    }

}
