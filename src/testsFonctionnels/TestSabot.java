package testsFonctionnels;

import java.util.Iterator;

import cartes.*;
import jeu.Sabot;

public class TestSabot {

	private static final int CAPACITE_MAXIMALE = 106; //**est ce que j'ajoute les cartes memo ?

	public static void main(String[] args) {
		Carte [] cartes = new Carte [CAPACITE_MAXIMALE];
		int indice = 0 ; 
		//Borne
		for (int i = 0; i < 10; i++) {
            cartes[indice++] = new Borne(25); 
        }
        for (int i = 0; i < 10; i++) {
            cartes[indice++] = new Borne(50); 
        }
        for (int i = 0; i < 10; i++) {
            cartes[indice++] = new Borne(75); 
        }
        for (int i = 0; i < 12; i++) {
            cartes[indice++] = new Borne(100); 
        }
        for (int i = 0; i < 4; i++) {
            cartes[indice++] = new Borne(200); 
        }

        //Parade
        for (int i = 0; i < 14; i++) {
            cartes[indice++] = new Parade(Type.FEU); 
        }
        for (int i = 0; i < 6; i++) {
            cartes[indice++] = new Parade(Type.LIMITE); 
        }
        for (int i = 0; i < 6; i++) {
            cartes[indice++] = new Parade(Type.ESSENCE); 
        }
        for (int i = 0; i < 6; i++) {
            cartes[indice++] = new Parade(Type.CREVAISON); 
        }
        for (int i = 0; i < 6; i++) {
            cartes[indice++] = new Parade(Type.ACCIDENT); 
        }

        //Attaque
        for (int i = 0; i < 5; i++) {
            cartes[indice++] = new Attaque(Type.FEU); 
        }
        for (int i = 0; i < 4; i++) {
            cartes[indice++] = new Attaque(Type.LIMITE); 
        }
        for (int i = 0; i < 3; i++) {
            cartes[indice++] = new Attaque(Type.ESSENCE); 
        }
        for (int i = 0; i < 3; i++) {
            cartes[indice++] = new Attaque(Type.CREVAISON); 
        }
        for (int i = 0; i < 3; i++) {
            cartes[indice++] = new Attaque(Type.ACCIDENT);
        }
        // Bottes
        cartes[indice++] = new Botte(Type.LIMITE); 
        cartes[indice++] = new Botte(Type.ESSENCE);
        cartes[indice++] = new Botte(Type.CREVAISON);
        cartes[indice++] = new Botte(Type.ACCIDENT);
        
		Sabot sabot = new Sabot (cartes);
		
//		while (!sabot.estVide()) {
//			Carte carte = sabot.piocher();
//			System.out.println("je pioche " + carte);
//		}
		
		for (Iterator <Carte>iter=sabot.iterator(); iter.hasNext(); ) {
			Carte carte = iter.next();
			System.out.println("je pioche " + carte + ".");
			iter.remove();
//			try {
//	            sabot.piocher(); 
//	        } catch (IllegalStateException e) {
//	            System.out.println("Le sabot est vide"); // **si je fait comme ça il me dit que le sabot a été modifier
//	        }
		}
	}
}
