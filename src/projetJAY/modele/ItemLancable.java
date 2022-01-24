package projetJAY.modele;

public class ItemLancable extends Item {
	
	/*
	 * Tous nos Items sont des armes. Donc dans l'id�ale, il aurait fallut que ItemLancable extends Arme et soit appel� "ArmeLancable".
	 * On r�p�te ici des attributs d�j� pr�sent dans Arme � cause de �a. Ce choix est du � une question de temps.
	 */
	
	private int vitesse;
	private int degats;
	private int dureeDeVie;
	
	public ItemLancable(int positionX, int positionY, int vitesse, int degats, int dureeDeVie, ZoneDeJeu jeu) {
		super(positionX, positionY, jeu);
		this.vitesse = vitesse;
		this.degats = degats;
		this.dureeDeVie = dureeDeVie;
	}
	
	public ItemLancable(int vitesse, int degats, int dureeDeVie, ZoneDeJeu jeu) {
		super(jeu);
		this.vitesse = vitesse;
		this.degats = degats;
		this.dureeDeVie = dureeDeVie;
	}
	
	// Getters et Setters
	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getDureeDeVie() {
		return dureeDeVie;
	}

	public void setDureeDeVie(int dureeDeVie) {
		this.dureeDeVie = dureeDeVie;
	}

	public void seDeplace() {		// Pas abstract, j'ai d�cid� que tous les ItemsLancable (Fl�che et Shuriken) se d�placent de la m�me mani�re
		int posXLink = getJeu().getLink().getPositionX();
		int posYLink = getJeu().getLink().getPositionY();
		
		if (this.getPositionX() > posXLink) {
			this.setPositionX(this.getPositionX() - 16);
		} else if (this.getPositionX() < posXLink) {
			this.setPositionX(this.getPositionX() + 16);
		} else if (this.getPositionY() > posYLink) {
			this.setPositionY(this.getPositionY() - 16);
		} else {
			this.setPositionY(this.getPositionY() + 16);
		}
		
		this.dureeDeVie--;
	}

	public boolean estUsee() {
		return this.dureeDeVie <= 0;
	}
	
	
}
