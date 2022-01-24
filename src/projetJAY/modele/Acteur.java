package projetJAY.modele;


public abstract class Acteur extends Composant {
	


	private boolean veutSeDeplacerHaut;
	private boolean veutSeDeplacerBas;
	private boolean veutSeDeplacerGauche;
	private boolean veutSeDeplacerDroite;
	

	private ZoneDeJeu jeu;
	private int vitesse;
	
	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public Acteur(int positionX, int positionY, ZoneDeJeu jeu, int vitesse) {
		super(positionX, positionY, jeu);
		
		this.veutSeDeplacerHaut = false;
		this.veutSeDeplacerBas = false;
		this.veutSeDeplacerGauche = false;
		this.veutSeDeplacerDroite = false;

		this.vitesse = vitesse;
		this.jeu = jeu;
	}

	// Getters et Setters des bool�ens
	public boolean isVeutSeDeplacerHaut() {
		return veutSeDeplacerHaut;
	}

	public void setVeutSeDeplacerHaut(boolean veutSeDeplacerHaut) {
		this.veutSeDeplacerHaut = veutSeDeplacerHaut;
	}

	public boolean isVeutSeDeplacerBas() {
		return veutSeDeplacerBas;
	}

	public void setVeutSeDeplacerBas(boolean veutSeDeplacerBas) {
		this.veutSeDeplacerBas = veutSeDeplacerBas;
	}

	public boolean isVeutSeDeplacerGauche() {
		return veutSeDeplacerGauche;
	}

	public void setVeutSeDeplacerGauche(boolean veutSeDeplacerGauche) {
		this.veutSeDeplacerGauche = veutSeDeplacerGauche;
	}

	public boolean isVeutSeDeplacerDroite() {
		return veutSeDeplacerDroite;
	}

	public void setVeutSeDeplacerDroite(boolean veutSeDeplacerDroite) {
		this.veutSeDeplacerDroite = veutSeDeplacerDroite;
	}
	

	
	public ZoneDeJeu getJeu() {
		return jeu;
	}
	
	
	public void traiterDeplacement() throws HorsLimiteMapException {
		final int deltaDeplacement = this.vitesse;

		int positionActuelleX = this.getPositionX();
		int positionActuelleY = this.getPositionY();
		
		if (positionActuelleX<0 || positionActuelleX>Terrain.nbTuilesParLigne*Terrain.tailleDesTuiles || positionActuelleY<0 || positionActuelleY>Terrain.nbTuilesParColonne*Terrain.tailleDesTuiles)
			throw new HorsLimiteMapException();
		
		if (this.isVeutSeDeplacerHaut()) {
			// On v�rifie si, une fois le d�placement appliqu�, le composants ne sera pas
			// bugg�. A L'AVENIR, IL FAUDRA METTRE PLUSIEURS METHODES peutSeDeplacerIci(),
			// UNE POUR CHAQUE TYPE DE COMPOSANT
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(positionActuelleX,
					positionActuelleY - deltaDeplacement) == true) {
				// Si le d�placement est faisable, on l'applique
				this.setPositionY(positionActuelleY - deltaDeplacement);
			}
		}
		if (this.isVeutSeDeplacerBas()) {
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(positionActuelleX,
					positionActuelleY + deltaDeplacement) == true) {
				this.setPositionY(positionActuelleY + deltaDeplacement);
			}
		}
		if (this.isVeutSeDeplacerGauche()) {
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(positionActuelleX - deltaDeplacement,
					positionActuelleY) == true) {
				this.setPositionX(positionActuelleX - deltaDeplacement);
			}
		}
		if (this.isVeutSeDeplacerDroite()) {
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(positionActuelleX + deltaDeplacement,
					positionActuelleY) == true) {
				this.setPositionX(positionActuelleX + deltaDeplacement);
			}
		}
		
		if (this instanceof Joueur && (this.isVeutSeDeplacerHaut() || this.isVeutSeDeplacerBas() || this.isVeutSeDeplacerGauche() || this.isVeutSeDeplacerDroite())) {
			System.out.println("x=" + this.getPositionX() + " ; y=" + this.getPositionY());
		}
	}
	
}
