package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import cartes.JeuDeCartes;


public class GestionCartes {
	
	private static Random random = new Random();
	private GestionCartes () {
		throw new IllegalStateException("Utility class");
	}
	
	public static <E> E extraire (List<E> liste) {

        int indiceElement = random.nextInt(liste.size()); 
		return liste.remove(indiceElement);
	}

	
	public static <E> List<E> melanger (List<E> listeCartes) {
		ArrayList<E> listeMelangee  = new ArrayList<> (listeCartes);
		Collections.shuffle(listeMelangee); 
		listeCartes.clear();
		return  listeMelangee;
	}

	public static <E> boolean verifierMelange (List<E> listeCarteNonMelangee , List<E> listeCartes) {
		if (listeCarteNonMelangee.size() != listeCartes.size()) {
			return false;
		}
		
		for (E element : listeCarteNonMelangee) {
			if (Collections.frequency(listeCarteNonMelangee, element) != (Collections.frequency(listeCartes, element))) {
				return false ;
			}
		}
		return true;
	}
	
	
	public static <E> List<E> rassembler (List<E> listeCartes) {
		ArrayList<E> nvListe = new ArrayList<>();
		Set<E> elementsAjoutes = new HashSet<>() ;
		for (E eCourant : listeCartes) {
			 if (!elementsAjoutes.contains(eCourant)) {
				 int nbr = countNbOccurence(listeCartes, eCourant) ;
				 for (int j = 0 ; j < nbr ; j++) {
					 nvListe.add(eCourant);
				 }
				 
				 elementsAjoutes.add(eCourant);
			 }
		
		}
		return nvListe ;
	}
	
	public static <E> int countNbOccurence (List<E> listeCartes , E element) {
		int nbr = 0 ; 
		for (int i = 0 ; i < listeCartes.size() ; i++ ) {
			if (listeCartes.get(i).equals(element)) {
				nbr++;
			}
		}
		return nbr ;
	}
	
	
	public static <E> boolean verifierRassemblement(List<E> liste) {
	    for (ListIterator<E> iter1 = liste.listIterator(); iter1.hasNext();) {
	        E elementCourant = iter1.next(); 
	        if (iter1.previousIndex() > 0 && !elementCourant.equals(liste.get(iter1.previousIndex() - 1))) 
	            for (ListIterator<E> iter2 = liste.listIterator(iter1.previousIndex()); iter2.hasNext();) {
	                E nextElement = iter2.next();
	                if (nextElement.equals(liste.get(iter1.previousIndex() - 1))) {
	                	System.out.println(liste.size());
	                	return false; 
	                }
	            }
	        }
	    
	    return true; 
	}
	
	

	
}
	
	
	
	