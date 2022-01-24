package projetJAY.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import projetJAY.modele.Baton;
import projetJAY.modele.Composant;

public class BatonVue {
	
	private Baton batonDuModele;
	private Pane paneJeu;
	
	public BatonVue(Pane paneJeu, Baton batonDuModele) {
		this.batonDuModele = batonDuModele;
		this.paneJeu = paneJeu;
	}
	
	public void creeBaton() {
		Image chargementImageBaton = new Image("file:src/projetJAY/ressources/batonMap.png");
		ImageView imageBaton = new ImageView(chargementImageBaton);
		imageBaton.setId(batonDuModele.getIdComposant());
		imageBaton.translateXProperty().bind(batonDuModele.getPositionXProperty().add( (-1) * chargementImageBaton.getWidth() /2));		// -1 pour la soustraction
		imageBaton.translateYProperty().bind(batonDuModele.getPositionYProperty().add( (-1) * chargementImageBaton.getHeight() /2));	// Idem
		
		this.paneJeu.getChildren().add(imageBaton);
	}
}
