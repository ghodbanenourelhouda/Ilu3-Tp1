package cartes;

public class Attaque extends Bataille{
	public Attaque(Type type) {
		super (type);
	}
	
	public String toString() {
		Type type = getType();
		return type.getAttaque();
	}
	
	@Override
	public Type getType() {
		return super.getType();
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj instanceof Attaque) {
			Attaque carte = (Attaque) obj ;
			return getType().equals(carte.getType());
		}
		return false ;
	}
}