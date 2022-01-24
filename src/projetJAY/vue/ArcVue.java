package projetJAY.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import projetJAY.modele.Arc;
import projetJAY.modele.Baton;
import projetJAY.modele.Composant;

public class ArcVue {
	
	private Arc ArcDuModele;
	private Pane paneJeu;
	
	public ArcVue(Pane paneJeu, Arc ArcDuModele) {
		this.ArcDuModele = ArcDuModele;
		this.paneJeu = paneJeu;
	}
	
	public void creeArc() {
		Image chargementImageArc = new Image("file:src/projetJAY/ressources/ArcMap.png");
		ImageView imageArc = new ImageView(chargementImageArc);
		imageArc.setId(ArcDuModele.getIdComposant());
		imageArc.translateXProperty().bind(ArcDuModele.getPositionXProperty());		// -1 pour la soustraction
		imageArc.translateYProperty().bind(ArcDuModele.getPositionYProperty());	// Idem
		
		this.paneJeu.getChildren().add(imageArc);
	}
}	
