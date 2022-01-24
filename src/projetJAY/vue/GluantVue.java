package projetJAY.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import projetJAY.modele.Composant;
import projetJAY.modele.Gluant;
import projetJAY.modele.ZoneDeJeu;

public class GluantVue {

	private Gluant gluantDuModele;
	private Pane paneJeu;
	private ImageView ennemiGluant;

	public GluantVue(Gluant ennemiDuModele, Pane paneJeu) {
		this.gluantDuModele = ennemiDuModele;
		this.paneJeu = paneJeu;
	}
	
	public ImageView getEnnemiCree() {
		return ennemiGluant;
	}

	public void creerEnnemi() {
		Image GluantIMG = new Image("/projetJAY/ressources/Gluant.png");
		ImageView GluantSurLaVue = new ImageView();
		GluantSurLaVue.setImage(GluantIMG);
		GluantSurLaVue.setFitWidth(25);
		GluantSurLaVue.setFitHeight(25);
		GluantSurLaVue.translateXProperty().bind(gluantDuModele.getPositionXProperty());
		GluantSurLaVue.translateYProperty().bind(gluantDuModele.getPositionYProperty());
		GluantSurLaVue.setId(gluantDuModele.getIdComposant());
				
		this.paneJeu.getChildren().add(GluantSurLaVue);
		this.ennemiGluant = GluantSurLaVue;
	}
}
