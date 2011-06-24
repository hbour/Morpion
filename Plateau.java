
public class Plateau {
	int taillePlateau;
	char[][] plateau;
	
	public Plateau(int taille) {
		taillePlateau = taille;
		plateau = new char[taille][taille];
		for (int i=0; i<taille; i++) {
			for (int j=0; j<taille; j++) {
				plateau[i][j] = ' ';
			}
		}
	}
	
	public boolean jouer(int row, int col) {
		boolean ok = false;
		if (row < 0 || row > taillePlateau-1 || col < 0 || col > taillePlateau-1) {
			System.out.println("Cette case n'existe pas (coordonnées entre 1 et "+taillePlateau+").");
		} else if (plateau[row][col] == ' ') {
			plateau[row][col] = 'X';
			ok = true;
		} else {
			System.out.println("La case "+(row+1)+", "+(col+1)+" n'est pas vide.");
		}
		return ok;
	}
	
	public boolean fin() {
		boolean finDePartie = true;
		for (int row=0; row<taillePlateau; row++) {
			for (int col=0; col<taillePlateau; col++) {
				if (plateau[row][col] == ' ') {
					finDePartie = false;
				}
			}
		}
		return finDePartie;
	}
	
	public boolean victoire(char joueur) {
		boolean victoire = false;
		boolean vic;
		int row, col;
		
		// lignes.
		for (row=0; row<taillePlateau; row++) {
			vic = true;
			for (col=0; col<taillePlateau; col++) {
				if (plateau[row][col] != joueur) {
					vic = false;
					break;
				}
			}
			if (vic == true) {
				victoire = true;
				if (joueur == 'X') {
					System.out.println("Vous gagnez à la ligne "+(row+1));
				} else if (joueur == '0') {
					System.out.println("L'ordinateur gagne à la ligne "+(row+1));
				}
				
			}
		}
		
		// colonnes.
		for (col=0; col<taillePlateau; col++) {
			vic = true;
			for (row=0; row<taillePlateau; row++) {
				if (plateau[row][col] != joueur) {
					vic = false;
					break;
				}
			}
			if (vic == true) {
				victoire = true;
				if (joueur == 'X') {
					System.out.println("Vous gagnez à la colonne "+(col+1));
				} else if (joueur == '0') {
					System.out.println("L'ordinateur gagne à la colonne "+(col+1));
				}
			}
		}
		
		// diagonale 1.
		row = 0;
		col = 0;
		vic = true;
		do {
			if (plateau[row][col] != joueur) {
				vic = false;
				break;
			}
			row++;
			col++;
		} while (row < taillePlateau);
		if (vic == true) {
			victoire = true;
			if (joueur == 'X') {
				System.out.println("Vous gagnez en diagonale");
			} else if (joueur == '0') {
				System.out.println("L'ordinateur gagne en diagonale");
			}
		}
	
		
		// diagonale 2.
		row = 0;
		col = taillePlateau - 1;
		vic = true;
		do {
			if (plateau[row][col] != joueur) {
				vic = false;
				break;
			}
			row++;
			col--;
		} while (row < taillePlateau);
		if (vic == true) {
			victoire = true;
			if (joueur == 'X') {
				System.out.println("Vous gagnez en diagonale");
			} else if (joueur == '0') {
				System.out.println("L'ordinateur gagne en diagonale");
			}
		}
		
		
		return victoire;
	}
	
	public String toString() {
		String res = "";
		int a, b, c=0;
		
		for (a=0; a<taillePlateau; a++) {
			do {
				res += " ---";
				c++;
			} while (c < taillePlateau);
			res += "\r";
			c = 0;
			res += "|";
			for (b=0; b<taillePlateau; b++) {
				res += " "+plateau[a][b]+" |";
			}
			res += "\r";
		}
		do {
			res += " ---";
			c++;
		} while (c < taillePlateau);
		return res;
	}
	
	
}
