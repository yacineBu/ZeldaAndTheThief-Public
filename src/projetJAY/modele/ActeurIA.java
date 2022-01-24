package projetJAY.modele;

public abstract class ActeurIA extends Acteur {
	
	public ActeurIA(int positionX, int positionY, ZoneDeJeu jeu) {
		super(positionX, positionY, jeu, 2);
	}
	
	public abstract void agir(int tempsEcoule);
	
	public abstract void seDeplaceAleatoirement(int tempsEcoule);
}

