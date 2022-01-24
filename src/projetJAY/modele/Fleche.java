package projetJAY.modele;

public class Fleche extends ItemLancable {
		
	final static int vitesse = 3;
	final static int degats = 20;
	final static int dureeDeVie = 100;

	public Fleche(int positionX, int positionY, ZoneDeJeu jeu) {
	        super(positionX, positionY, vitesse, degats, dureeDeVie, jeu);
	}
	
	public Fleche(ZoneDeJeu jeuTemp) {
		super(vitesse, degats, dureeDeVie, jeuTemp);
	}

}
