package projetJAY.modele;

public class Arc extends Arme{
	
	public Arc(int positionX, int positionY, int tempsCooldown, ZoneDeJeu jeu) {
		super(positionX, positionY, tempsCooldown, jeu);
		this.setPtAttaque(10);
	}
	
	public Arc(int tempsCooldown, ZoneDeJeu jeu) {
		super(tempsCooldown, jeu);
		this.setPtAttaque(10);
	}

	@Override
	public void utiliserArme() {	
		// On tire une fl�che.
		// Techniquement parlant on ajoute � la liste d'item de jeu une Fleche. Celle-ci se d�placera toute seule ensuite grace au code dans evolution().
		getJeu().getItems().add(new Fleche(getJeu().getLink().getPositionX(), getJeu().getLink().getPositionY(), getJeu()));
//		System.out.println("nouvelle fleche dans le mod�le !");
	}
}
