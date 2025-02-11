package cartes;

public class Borne extends Carte {
	private int km ;
	
	public Borne(int km) {
		this.km = km ;
	}
	
	@Override
	public String toString() {
		return km + "KM";
	}
	public int getKm() {
		return km;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj instanceof Borne carte) {
			return km == carte.km;
		}
		return false ;
	}

	
}
