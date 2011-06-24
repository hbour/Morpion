
public class IA {
	int taillePlateau;
	double[][] plateauBase;		// La valeur de chaque case est fonction de son potentiel
							// à faire une ligne et de sa proximité du centre.
	double[][] plateau;
	
	public IA(int taille) {
		plateauBase = new double[taille][taille];
		plateau = new double[taille][taille];
		taillePlateau = taille;
		
		int row, col, i;
		
		// Donne à chaque case une valeur de 2.
		for (row=0; row<taille; row++) {
			for (col=0; col<taille; col++) {
				plateauBase[row][col] = 2.0;
			}
		}
		
		// Incrémente la diagonale 1.
		for (row=0, col=0; row<taille; row++, col++) {
			plateauBase[row][col] += 1.0;
		}
		
		// Incrémente la diagonale 2.
		for (row=0, col=taille-1; row<taille; row++, col--) {
			plateauBase[row][col] += 1.0;
		}
		
		int centre = (taille%2==0) ? (taille/2) : ((taille+1)/2);
		// Augmente la valeur des cases les plus au centre. 
		for (i=1; i<=centre; i++) {
			for (row=i; row<taille-i; row++) {
				for (col=i; col<taille-i; col++) {
					plateauBase[row][col] += 0.4;
				}
			}
		}
	}

	public void jouer(Plateau p) {
		
		for (int row=0; row<taillePlateau; row++) {
			for (int col=0; col<taillePlateau; col++) {
				plateau[row][col] = plateauBase[row][col];
			}
		}

		casesJouees(p);
		analyser(p);
		placerPiece(p);
	}
	
	public void casesJouees(Plateau p) {
		int row, col;
		for (row=0; row<taillePlateau; row++) {
			for (col=0; col<taillePlateau; col++) {
				if (p.plateau[row][col] != ' ') {
					plateau[row][col] = 0;
				}
			}
		}
	}
	
	public void analyser(Plateau p) {
		int row, col;
		int nbX, nbO;
		
		// lignes.
		for (row=0; row<taillePlateau; row++) {
			nbX = 0;
			nbO = 0;
			for (col=0; col<taillePlateau; col++) {
				if (p.plateau[row][col] == 'X') {
					nbX++;
				}
				if (p.plateau[row][col] == 'O') {
					nbO++;
				}
			}
			if (nbX == taillePlateau - 1) {					// Empêche l'adversaire de terminer une ligne.
				for (col=0; col<taillePlateau; col++) {
					plateau[row][col] *= 100.0;
				}
			} else if (nbO == 0 && nbX > 0) {				// Bloque l'adversaire de manière anticipée.
				for (col=0; col<taillePlateau; col++) {
					plateau[row][col] *= nbX;
				}
			} else if (nbX == 0) {							// Faire une ligne.
				for (col=0; col<taillePlateau; col++) {
					plateau[row][col] *= (nbO+1);
				}
			}
		}
		
		// colonnes.
		for (col=0; col<taillePlateau; col++) {
			nbX = 0;
			nbO = 0;
			for (row=0; row<taillePlateau; row++) {
				if (p.plateau[row][col] == 'X') {
					nbX++;
				}
				if (p.plateau[row][col] == 'O') {
					nbO++;
				}
			}
			if (nbX == taillePlateau - 1) {
				for (row=0; row<taillePlateau; row++) {
					plateau[row][col] *= 100.0;
				}
			} else if (nbO == 0) {
				for (row=0; row<taillePlateau; row++) {
					plateau[row][col] *= (nbX+1);
				}
			} else if (nbX == 0) {
				for (row=0; row<taillePlateau; row++) {
					plateau[row][col] *= (nbO+1);
				}
			}
		}
		
		// diagonale 1.
		nbX = 0;
		nbO = 0;
		for (row=0, col=0; row<taillePlateau; row++, col++) {
			if (p.plateau[row][col] == 'X') {
				nbX++;
			}
			if (p.plateau[row][col] == 'O') {
				nbO++;
			}
		}
		if (nbX == taillePlateau - 1) {
			for (row=0, col=0; row<taillePlateau; row++, col++) {
				plateau[row][col] *= 100.0;
				}
		} else if (nbO == 0) {
			for (row=0, col=0; row<taillePlateau; row++, col++) {
				plateau[row][col] *= (nbX+1);
			}
		} else if (nbX == 0) {
			for (row=0, col=0; row<taillePlateau; row++, col++) {
				plateau[row][col] *= (nbO+1);
			}
		}
		
		
		
		// diagonale 2.
		nbX = 0;
		nbO = 0;
		for (row=0, col=taillePlateau-1; row<taillePlateau; row++, col--) {
			if (p.plateau[row][col] == 'X') {
				nbX++;
			}
			if (p.plateau[row][col] == 'O') {
				nbO++;
			}
		}
		if (nbX == taillePlateau - 1) {
			for (row=0, col=taillePlateau-1; row<taillePlateau; row++, col--) {
				plateau[row][col] *= 100.0;
			}
		} else if (nbO == 0) {
			for (row=0, col=taillePlateau-1; row<taillePlateau; row++, col--) {
				plateau[row][col] *= (nbX+1);
			}
		} else if (nbX == 0) {
			for (row=0, col=taillePlateau-1; row<taillePlateau; row++, col--) {
				plateau[row][col] *= (nbO+1);
			}
		}
		
	}
	
	public void placerPiece(Plateau p) {
		int row, col;
		double valeurMax = 0;
		int valeurMaxRow = 0, valeurMaxCol = 0;
		
		for (row=0; row<taillePlateau; row++) {
			for (col=0; col<taillePlateau; col++) {
				if (plateau[row][col] > valeurMax) {
					valeurMax = plateau[row][col];
					valeurMaxRow = row;
					valeurMaxCol = col;
				}
			}
		}
		p.plateau[valeurMaxRow][valeurMaxCol] = 'O';
			
	}

}
