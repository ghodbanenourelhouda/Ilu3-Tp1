package cartes;

public class JeuDeCartes {
	private Configuration [] typesDeCarte = {
	new Configuration (new Borne(25) , 10),
	new Configuration (new Borne(50) , 10),
	new Configuration (new Borne(75) , 10),
	new Configuration (new Borne(100) , 12),
	new Configuration (new Borne(200) , 4),
	new Configuration (new Parade(Type.FEU) , 14),
	new Configuration (new Parade (Type.LIMITE), 6),
	new Configuration (new Parade (Type.ESSENCE), 6),
	new Configuration (new Parade (Type.CREVAISON), 6),
	new Configuration (new Parade (Type.ACCIDENT), 6),
	new Configuration (new Attaque (Type.FEU), 5),
	new Configuration (new Attaque (Type.LIMITE), 4),
	new Configuration (new Attaque (Type.ESSENCE), 3),
	new Configuration (new Attaque (Type.CREVAISON), 3),
	new Configuration (new Attaque (Type.ACCIDENT), 3),
	new Configuration (new Botte (Type.LIMITE), 1),
	new Configuration (new Botte (Type.ESSENCE), 1),
	new Configuration (new Botte (Type.CREVAISON), 1),
	new Configuration (new Botte (Type.ACCIDENT), 1)
	};

	public String affichageJeuDeCartes() {
	    StringBuilder affichage = new StringBuilder("JEU :\n\n");
	    for (Configuration config : typesDeCarte) {
	        Carte carte = config.getCarte();
	        int nbExemplaires = config.getNbExemplaires();
	        affichage.append(nbExemplaires)
	                 .append(" ")
	                 .append(carte.toString())
	                 .append("\n");
	    }
	    return affichage.toString();
	}
	

	private static class Configuration {
		private int nbExemplaires;
		private Carte carte; 
		
		private Configuration (Carte carte, int nbExemplaires) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}
	
		
	}

}

