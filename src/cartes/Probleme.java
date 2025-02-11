package cartes;

public abstract class Probleme extends Carte{
	private Type type;
	
	protected Probleme (Type type) {
		this.type = type ;
		
	}	
	
	public Type getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null || getClass() != obj.getClass()) {  
	        return false;
	    }
	    Probleme carte = (Probleme) obj;  
	    return type.equals(carte.type); 
	}


}
