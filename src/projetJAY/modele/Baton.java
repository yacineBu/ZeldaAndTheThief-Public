package projetJAY.modele;

public class Baton extends Arme {
	
	final int tempsCooldown = 1000;

	public Baton(int positionX, int positionY, int tempsCooldown, ZoneDeJeu jeu) {
		super(positionX, positionY, tempsCooldown, jeu);
		this.setPxDePortee(20);
		this.setPtAttaque(3);
	}
	
	public Baton(int tempsCooldown, ZoneDeJeu jeu) {
		super(tempsCooldown, jeu);
		this.setPxDePortee(20);
		this.setPtAttaque(3);
	}

	@Override
	public void utiliserArme() {
		Ennemi cible = this.ennemiLePlusProche(getPxDePortee());		// !!!!!!
		
		if (cible != null) {
			cible.setPvEnnemi(cible.getPvEnnemi() - getPtAttaque());
			
			System.out.println(cible.getClass() + " : " + cible.getPvEnnemi() + " pv restant");
		}
	}
	
	

}
