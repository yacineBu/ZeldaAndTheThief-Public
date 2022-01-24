package projetJAY.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.TilePane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
import projetJAY.modele.Composant;
//import projetJAY.modele.ZoneDeJeu;

public class BossFinalVue {
	
	private Composant ennemiDuModele;
	private Pane paneJeu;
	private ImageView ennemiRoiGluant;
	
	public BossFinalVue(Composant ennemiDuModele, Pane paneJeu) {
		this.ennemiDuModele = ennemiDuModele;
		this.paneJeu = paneJeu;
	}
	
	public ImageView getEnnemiCree() {
		return ennemiRoiGluant;
	}
	
	public void creerEnnemi() {
		/*Circle ennemiRoiGluant = new Circle();
		ennemiRoiGluant.setTranslateX(ennemiDuModele.getPositionX());
		ennemiRoiGluant.setTranslateY(ennemiDuModele.getPositionY());
		ennemiRoiGluant.setRadius(20);
		ennemiRoiGluant.setFill(Color.PURPLE);
		ennemiRoiGluant.setId(ennemiDuModele.getIdComposant());
				
		this.paneJeu.getChildren().add(ennemiRoiGluant);
		
		this.ennemiRoiGluant = ennemiRoiGluant;
		ennemiRoiGluant.toFront();*/
		
		Image RoiGluantIMG = new Image("/projetJAY/ressources/RoiGluant.png");
		ImageView RoiGluantSurLaVue = new ImageView();
		RoiGluantSurLaVue.setImage(RoiGluantIMG);
		RoiGluantSurLaVue.setFitWidth(100);
		RoiGluantSurLaVue.setFitHeight(100);
		RoiGluantSurLaVue.setX(-50);
		RoiGluantSurLaVue.setY(-50);
		RoiGluantSurLaVue.setTranslateX(ennemiDuModele.getPositionX());
		RoiGluantSurLaVue.setTranslateY(ennemiDuModele.getPositionY());
		RoiGluantSurLaVue.setId(ennemiDuModele.getIdComposant());
		
		this.paneJeu.getChildren().add(RoiGluantSurLaVue);
		this.ennemiRoiGluant = RoiGluantSurLaVue;
		ennemiRoiGluant.toFront();
		
	}
}
