package projetJAY.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Ennemi extends ActeurIA {

	private IntegerProperty pvEnnemi;
	private int cooldown;			// En milisecondes
	private boolean estEnPause;		
	private int dateDePause;		// Date en milisecondes
	private int dateDeMarche;		// Date en milisecondes
	private int tempsMarche;
	private int tempsPause;
	private int dateAttaque;		// Date en milisecondes
	private int rayonAttaque;
	
	public Ennemi(int positionX, int positionY, ZoneDeJeu jeu, int cooldown, int pvEnnemi, int rayonAttaque) {
		super(positionX, positionY, jeu);
		this.cooldown = cooldown;
		this.pvEnnemi = new SimpleIntegerProperty(pvEnnemi);
		this.estEnPause = true;
		this.dateDePause = 0;
		this.dateAttaque = 0;
		this.rayonAttaque = rayonAttaque;
	}
	
	public int getDateDePause() {
		return dateDePause;
	}
	
	public void setDateDePause(int dateDePause) {
		this.dateDePause = dateDePause;
	}
	
	public int getDateDeMarche() {
		return dateDeMarche;
	}
	
	public void setDateDeMarche(int dateDeMarche) {
		this.dateDeMarche = dateDeMarche;
	}
	
	public boolean getEstEnPause() {
		return estEnPause;
	}
	
	public void setEstEnPause(boolean estEnPause) {
		this.estEnPause = estEnPause;
	}
	
	public int getTempsMarche() {
		return tempsMarche;
	}
	
	public int getTempsPause() {
		return tempsPause;
	}
	
	public IntegerProperty getPvEnnemiProperty() {
		return pvEnnemi;
	}
	
	public int getPvEnnemi() {
		return pvEnnemi.getValue();
	}
	
	public void setPvEnnemi(int pvEnnemi) {
		this.pvEnnemi.setValue(pvEnnemi);
	}
	

	public void setDateAttaque(int dateAttaque) {
		this.dateAttaque = dateAttaque;
	}
	
	@Override
	public abstract void agir(int tempsEcoule);
	
	public abstract void attaquer(int tempsEcoule);
	
	public boolean focusSurLink() {
		return this.distanceComposantJoueur() < this.rayonAttaque;
	}
	
	public boolean peutReattaquer(int tempsEcoule) {
		return tempsEcoule - this.dateAttaque >= this.cooldown;
	}
	

	
	public boolean estMort() {
		if(this.getPvEnnemi() <= 0) {
			return true;
		}
		return false;
	}
	
	public void seDeplaceVersLink() {
		if(this.getPositionX() > getJeu().getLink().getPositionX()) {
			this.setVeutSeDeplacerGauche(false);
			this.setVeutSeDeplacerDroite(false);
			this.setVeutSeDeplacerHaut(false);
			this.setVeutSeDeplacerBas(false);
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(getPositionX() - this.getVitesse(),getPositionY()) == true) {
				this.setPositionX(this.getPositionX() - this.getVitesse());
			}
		}
		else if(this.getPositionX() < getJeu().getLink().getPositionX()) {
			this.setVeutSeDeplacerGauche(false);
			this.setVeutSeDeplacerDroite(false);
			this.setVeutSeDeplacerHaut(false);
			this.setVeutSeDeplacerBas(false);
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(getPositionX() + this.getVitesse(),getPositionY()) == true) {
				this.setPositionX(this.getPositionX() + this.getVitesse());
			}
		}
		if (this.getPositionY() > getJeu().getLink().getPositionY()) {
			this.setVeutSeDeplacerHaut(false);
			this.setVeutSeDeplacerBas(false);
			this.setVeutSeDeplacerGauche(false);
			this.setVeutSeDeplacerDroite(false);
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(getPositionX(),getPositionY() - this.getVitesse()) == true)
				this.setPositionY(this.getPositionY() - this.getVitesse());
		}
		else if(this.getPositionY() < getJeu().getLink().getPositionY()) {
			this.setVeutSeDeplacerBas(false);
			this.setVeutSeDeplacerHaut(false);
			this.setVeutSeDeplacerGauche(false);
			this.setVeutSeDeplacerDroite(false);
			if (this.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(getPositionX(),getPositionY() + this.getVitesse()) == true) {
				this.setPositionY(this.getPositionY() + this.getVitesse());
			}
		}

	}
}
