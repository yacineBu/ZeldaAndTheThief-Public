package projetJAY.modele;

public class Shuriken extends Item {
	
	private ZoneDeJeu jeu;
	final int vitesseShuriken = 3;
	final int degatsShuriken = 20;
	private int tempsEcoule = 20;
	
	public Shuriken(int positionX, int positionY, ZoneDeJeu jeu) {
		super(positionX, positionY, jeu);
		this.jeu = jeu;
		
	}

	public int getVitesseFleche() {
		return vitesseShuriken;
	}

	public int getDegatsFleche() {
		return degatsShuriken;
	}
	
	public boolean estUsee() {
		if(tempsEcoule == 0) {
			return true;
		}
		return false;
	}
	
	public void  seDeplace() {
		int x = jeu.getLink().getPositionX();
		int y = jeu.getLink().getPositionY();
	    //Tire une fl�che 
	
			if(this.getPositionX() > x) {
				this.setPositionX(this.getPositionX()-16);
			}
			else if(this.getPositionX() < x) {
				this.setPositionX(this.getPositionX()+16);
			}
			else if(this.getPositionY() > y) {
				this.setPositionY(this.getPositionY()-16);
			}
			else {
				this.setPositionY(this.getPositionY()+16);
			}
			tempsEcoule--;
	}
}
