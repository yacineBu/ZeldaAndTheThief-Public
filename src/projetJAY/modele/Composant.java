package projetJAY.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Composant {

	private IntegerProperty positionX;		// positionX >0 et <1920
	private IntegerProperty positionY;		// positionY >0 et <1440

	private ZoneDeJeu jeu;
	
	private static int compteur = 1;
	private String idComposant;

	
	public Composant(int positionX, int positionY, ZoneDeJeu jeu) {
		this.positionX = new SimpleIntegerProperty(positionX);
		this.positionY = new SimpleIntegerProperty(positionY);
		
		this.idComposant = "" + compteur;
		compteur++;
		
		this.jeu = jeu;
	}
	
	public Composant(ZoneDeJeu jeu) {
		this.positionX = null;
		this.positionY = null;
		
		this.idComposant = "" + compteur;
		compteur++;
		
		this.jeu = jeu;
	}

	
	// Getters et Setters des 2 propertys
	public int getPositionX() {
		return positionX.getValue();
	}

	public IntegerProperty getPositionXProperty() {
		return this.positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX.setValue(positionX);
	}

	public int getPositionY() {
		return positionY.getValue();
	}

	public IntegerProperty getPositionYProperty() {
		return this.positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY.setValue(positionY);
	}

	
	public String getIdComposant() {
		return this.idComposant;
	}
	
	public ZoneDeJeu getJeu() {
		return jeu;
	}
	
	/*
	 * Parcours la liste d'acteurs dans ZoneDeJeu puis retourne l'ennemi le plus proche du joueur. Si aucun ennemi n'est présent sous rayonMaximalTolere, elle renvoit null.
	 */
	public Ennemi ennemiLePlusProche(int rayonMaximalTolere) {
		double distanceEnnemiRegardeJoueur;
		Ennemi ennemiLePlusProche = null;
		double distanceEnnemiLePlusProcheJoueur = 0;
		
		for (ActeurIA ennemiRegarde : this.jeu.getActeursIA()) {
			if (ennemiRegarde instanceof Ennemi) {

				// On calcul la distance entre ennemiRegarde et le joueur
				distanceEnnemiRegardeJoueur = ennemiRegarde.distanceComposantJoueur();

				// Est-ce que cette distance est dans la hitzone
				if (distanceEnnemiRegardeJoueur < rayonMaximalTolere) {
					
					// Est-ce qu'on a personne dans ennemiLePlusProche OU est-ce que ennemiRegarde est plus proche que ennemiLePlusProche dï¿½jï¿½ prï¿½sent
					if (ennemiLePlusProche == null || distanceEnnemiRegardeJoueur < distanceEnnemiLePlusProcheJoueur) {
						ennemiLePlusProche = (Ennemi) ennemiRegarde;
						distanceEnnemiLePlusProcheJoueur = distanceEnnemiRegardeJoueur;
					}
				}
			}
		}
		
		// Si on a trouvï¿½ aucun acteur attaquable, on revoit null (ATTENTION BIEN RELIRE AVANT DE MODIFIER)
		return ennemiLePlusProche;
	}

	public double distanceComposantJoueur() {
		int posXJoueur = jeu.getLink().getPositionX();
        int posYJoueur = jeu.getLink().getPositionY();
        int posXComp = this.getPositionX();
        int posYComp = this.getPositionY();
        
        // On calcule la distance entre le composant et le joueur
        return Math.sqrt(Math.pow(posXJoueur - posXComp, 2) + Math.pow(posYJoueur - posYComp, 2));  
    }

}
