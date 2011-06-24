
public class Partie {
	Plateau plateau;
	IA ia;
	
	public Partie(int taillePlateau) {
		plateau = new Plateau(taillePlateau);
		ia = new IA(taillePlateau);
	}
	
	public void jouer() {
		boolean joueurGagne = false;
		boolean ordinateurGagne = false;
		boolean egalite = false;
		int col, row;
		do {
			System.out.println(plateau);
			System.out.println("Choisissez une case.");
			System.out.println("Ligne (de 1 à "+plateau.taillePlateau+") :");
			row = Lire.Entier() - 1;
			System.out.println("Colonne (de 1 à "+plateau.taillePlateau+") :");
			col = Lire.Entier() - 1;
			if (plateau.jouer(row, col)) {
				if (plateau.victoire('X')) {
					joueurGagne = true;
					System.out.println(plateau);
					System.out.println("Fin de partie.");
				} else {
					ia.jouer(plateau);
					if (plateau.victoire('O')) {
						ordinateurGagne = true;
						System.out.println(plateau);
						System.out.println("Fin de partie.");
					}
				}
			}
			if (plateau.fin() && joueurGagne == false && ordinateurGagne == false) {
				egalite = true;
				System.out.println(plateau);
				System.out.println("Fin de partie. Egalité.");
			}
		} while (joueurGagne == false && ordinateurGagne == false && egalite == false);
	}
	
	
}
