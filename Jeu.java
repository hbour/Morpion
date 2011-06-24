
public class Jeu {

	
	public static void main(String[] args) {
		System.out.println("\rM O R P I O N");
		System.out.println("\rTaille du plateau (standard: 3) :");
		int taillePlateau = Lire.Entier();
		Partie p = new Partie(taillePlateau);
		p.jouer();
		
	}

}
