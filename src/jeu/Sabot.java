package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable <Carte>{
	private Carte [] cartes;
	private int nbCartes;
	private static final int CAPACITE_MAXIMALE = 110;
	private int nombreOperations = 0;


	public Sabot(Carte [] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public int getNbCartes() {
        return nbCartes;
    }
	
	public Carte[] getCarte() {
		return cartes;
	}
	
	public boolean estVide () {
		return nbCartes == 0 ;
	}
	
	public Iterator<Carte> iterator() {
		 return new Iterateur();
	}
	
	private class Iterateur implements Iterator <Carte> {
		private int indiceIterateur = 0 ;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		public boolean hasNext () {
			return indiceIterateur < nbCartes;
		}
		
		public Carte next () {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes [indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new NoSuchElementException("Y a aucune carte restante");
			}
		}
		
		public void remove () {
			verificationConcurrence();
			if (! nextEffectue || nbCartes < 1) {
				throw new IllegalStateException("Aucune carte à supprimer");
			} 
			
			for (int i = indiceIterateur-1 ; i < nbCartes -1; i++) {
				cartes[i] = cartes[i+1];
			}
			
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nombreOperations++; 
			nombreOperationsReference++; 
		}
		
		private void verificationConcurrence(){
			 if (nombreOperations != nombreOperationsReference)
				 throw new ConcurrentModificationException("Le sabot a été modifier");
			 }
		}
	
	public void ajouterCarte (Carte carte) throws IllegalStateException {
		if ( nbCartes >=  CAPACITE_MAXIMALE) {
			throw new IllegalArgumentException("Dépacement de capacité ");
		} else {
			cartes[nbCartes] = carte;
			nbCartes++;
			nombreOperations++;
		}
	}
	
	public Carte piocher () {
		if (estVide()) {
			throw new IllegalStateException("Le sabot est vide");
		}
		
		Iterator<Carte> iterateur = iterator();
		Carte carte = iterateur.next();
		iterateur.remove();
		return carte;
		}
	
	
	
	
}


	
	
	

