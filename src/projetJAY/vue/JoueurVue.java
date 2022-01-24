package projetJAY.vue;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import projetJAY.modele.Joueur;
import projetJAY.modele.ZoneDeJeu;

public class JoueurVue {

	private Joueur joueurDuModele;
	private Pane paneJeu;
	//private Circle joueurCree;

	public JoueurVue(Joueur joueurDuModele, Pane paneJeu) {
		this.joueurDuModele = joueurDuModele;
		this.paneJeu = paneJeu;
	}
	
	/*public Circle getJoueurCree() {
		return joueurCree;
	}

	public void creerJoueur() {
		Circle linkSurLaVue = new Circle();
		linkSurLaVue.setTranslateX(joueurDuModele.getPositionX());
		linkSurLaVue.setTranslateY(joueurDuModele.getPositionY());
		linkSurLaVue.setRadius(10);
		linkSurLaVue.setId(joueurDuModele.getIdComposant());
				
		this.paneJeu.getChildren().add(linkSurLaVue);
		this.joueurCree = linkSurLaVue;
	}*/
}
