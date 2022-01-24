package projetJAY.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import projetJAY.modele.Composant;

public class SqueletteVue {

	private Composant ennemiDuModele;
	private Pane paneJeu;
	private ImageView ennemiSquelette;

	public SqueletteVue(Composant ennemiDuModele, Pane paneJeu) {
		this.ennemiDuModele = ennemiDuModele;
		this.paneJeu = paneJeu;
	}
	
	public ImageView getEnnemiCree() {
		return ennemiSquelette;
	}

	public void creerEnnemi() {

		Image SqueletteIMG = new Image("/projetJAY/ressources/Skeleton.png");
		ImageView SqueletteSurLaVue = new ImageView();
		SqueletteSurLaVue.setImage(SqueletteIMG);
		SqueletteSurLaVue.setFitWidth(25);
		SqueletteSurLaVue.setFitHeight(25);
		SqueletteSurLaVue.translateXProperty().bind(ennemiDuModele.getPositionXProperty());
		SqueletteSurLaVue.translateYProperty().bind(ennemiDuModele.getPositionYProperty());
		SqueletteSurLaVue.setId(ennemiDuModele.getIdComposant());
				
		this.paneJeu.getChildren().add(SqueletteSurLaVue);
		this.ennemiSquelette = SqueletteSurLaVue;
	}
}