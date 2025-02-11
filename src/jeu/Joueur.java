package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Limite;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
	private MainJoueur main;

	public Joueur(String nom, MainJoueur main) {
		this.nom = nom;
		this.main = main;
	}

	public MainJoueur getMain() {
		return main;
	}

	public String getNom() {
		return nom;
	}

	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur) {
			Joueur joueur = (Joueur) obj;
			return nom.equals(joueur.nom);
		}

		return false;
	}
	
	//TODO définir le hasCode
	
	@Override
	public String toString() {
		return "Joueur : " + nom;
	}

	public void donner(Carte carte) {
		main.prendre(carte);
	}

	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}

	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}

	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne) {
			Borne borne = (Borne) carte;
			return zoneDeJeu.estDepotBorneAutorise(borne.getKm());
		}

		if (carte instanceof Limite) {
			Limite limite = (Limite) carte;
			return zoneDeJeu.estDepotLimiteAutorise(limite);
		}

		if (carte instanceof Bataille) {
			Bataille bataille = (Bataille) carte;
			return zoneDeJeu.estDepotBatailleAutorise(bataille);
		}

		return false;

	}

	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> coupsValides = new HashSet<>();
		for (Joueur participant : participants) {
			for (Carte carte : main.getCartes()) {
				Coup coup = new Coup(this, carte, participant);
				if (coup.estValide()) {
					coupsValides.add(coup);
				}
			}
		}

		return coupsValides;
	}

	public Set<Coup> coupsDefausse() {
		Set<Coup> coups = new HashSet<>();
		for (Carte carte : main.getCartes()) {
			Coup coup = new Coup(this, carte, null);
			coups.add(coup);
		}

		return coups;
	}

	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}

	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsPossibles = coupsPossibles(participants);
		if (!coupsPossibles.isEmpty()) {
			return choisirCoupAleatoire(coupsPossibles);
		} else {
			Set<Coup> coupsDefausse = coupsDefausse();
			return choisirCoupAleatoire(coupsDefausse);
		}
	}

	private Coup choisirCoupAleatoire(Set<Coup> coups) {
		List<Coup> listeCoups = new ArrayList<>(coups);
		Random random = new Random();
		int index = random.nextInt(listeCoups.size());
		return listeCoups.get(index);
	}

	public String afficherEtatJoueur() {
	    Set<Botte> bottes = zoneDeJeu.getBottes();
	    String bottesString = bottes.isEmpty() ? "Aucune botte" : bottes.toString();
	    
	    boolean limitationDeVitesse = zoneDeJeu.donnerLimitationVitesse() < 200;

	    Bataille sommetBataille = zoneDeJeu.getSommetBataille();
	    String sommetBatailleString = (sommetBataille == null) ? "null" : sommetBataille.toString();

	    String cartesEnMain = main.getCartes().toString();

	    StringBuilder etat = new StringBuilder();
	    etat.append("État du joueur ").append(nom).append(" :\n");
	    etat.append("Bottes : ").append(bottesString).append("\n");
	    etat.append("Limitation de vitesse : ").append(limitationDeVitesse).append("\n");
	    etat.append("Sommet de la pile de bataille : ").append(sommetBatailleString).append("\n");
	    etat.append("Cartes en main : ").append(cartesEnMain);

	    return etat.toString();
	}


}
