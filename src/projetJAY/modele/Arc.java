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
		// On tire une flèche.
		// Techniquement parlant on ajoute à la liste d'item de jeu une Fleche. Celle-ci se déplacera toute seule ensuite grace au code dans evolution().
		getJeu().getItems().add(new Fleche(getJeu().getLink().getPositionX(), getJeu().getLink().getPositionY(), getJeu()));
//		System.out.println("nouvelle fleche dans le modèle !");
	}
}
