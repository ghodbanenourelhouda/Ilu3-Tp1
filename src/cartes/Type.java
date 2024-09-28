package cartes;

public enum Type {
	
	FEU ("Feu rouge", "Feu vert" , "Véhicule prioritaire"),
	ESSENCE ("Panne d'essence" ,"Essence" ,"Citerne d'essence"),
	CREVAISON ("Crevaison" , "Roue de secours", "Increvable"),
	ACCIDENT ("Accident" , "Réparation", "As du volant"),
	LIMITE ("Limite 50", "Fin de limite","Véhicule prioritaire" );
	
	
	private final String attaque ;
	private final String parade ;
	private final String botte ;
	
	Type (String attaque , String parade , String botte) {
		this.attaque = attaque;
		this.parade = parade ;
		this.botte = botte ;
	}
	
	public String getAttaque () {
		return attaque;
	}
	
	public String getParade () {
		return parade;
	}
	
	public String getBotte () {
		return botte;
	}
	
	
	
	
	
	
	
	
	
	
	
}
