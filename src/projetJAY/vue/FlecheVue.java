package projetJAY.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import projetJAY.modele.Fleche;

public class FlecheVue {
	private Fleche FlecheDuModele;
	private Pane paneJeu;
	
	public FlecheVue(Pane paneJeu, Fleche flecheDuModele) {
		this.FlecheDuModele = flecheDuModele;
		this.paneJeu = paneJeu;
	}
	
	public void creeFleche() {
		Image chargementImageFleche = new Image("file:src/projetJAY/ressources/FlecheMap.png");
		ImageView imageFleche = new ImageView(chargementImageFleche);
		imageFleche.setId(FlecheDuModele.getIdComposant());
		imageFleche.translateXProperty().bind(FlecheDuModele.getPositionXProperty());
		imageFleche.translateYProperty().bind(FlecheDuModele.getPositionYProperty());
		
		this.paneJeu.getChildren().add(imageFleche);
	}
}
