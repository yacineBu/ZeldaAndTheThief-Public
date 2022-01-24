package projetJAY.controleur;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import projetJAY.modele.ZoneDeJeu;


public class ControleurKeyPressed implements EventHandler<KeyEvent> {
	
	private ZoneDeJeu jeu;
	private ImageView linkDeLaVue;

	public ControleurKeyPressed(ZoneDeJeu jeu, ImageView linkDeLaVue) {
		this.jeu = jeu;
		this.linkDeLaVue = linkDeLaVue;
	}
	
	@Override
	public void handle(KeyEvent event) {
		
		switch (event.getCode()) {
		case Z:
			this.jeu.getLink().setVeutSeDeplacerHaut(true);
			linkDeLaVue.setImage(new Image("/projetJAY/ressources/LinkHaut.png"));
			break;
		case S:
			this.jeu.getLink().setVeutSeDeplacerBas(true);
			linkDeLaVue.setImage(new Image("/projetJAY/ressources/LinkBas.png"));
			break;
		case Q:
			this.jeu.getLink().setVeutSeDeplacerGauche(true);
			linkDeLaVue.setImage(new Image("/projetJAY/ressources/LinkGauche.png"));
			break;
		case D:
			this.jeu.getLink().setVeutSeDeplacerDroite(true);
			linkDeLaVue.setImage(new Image("/projetJAY/ressources/LinkDroite.png"));
			break;
		case NUMPAD5:
			this.jeu.getLink().setVeutPousser(true);
			break;
		case NUMPAD8:
			this.jeu.getLink().setVeutLancer(true);
			break;
		case NUMPAD7:
			this.jeu.getLink().setVeutAttaquer(true);
			break;
		case NUMPAD4:
			this.jeu.getLink().setVeutInteragir(true);
			break;
		case DIGIT1:
			this.jeu.getLink().setaDansSaMain(0);
			break;
		case DIGIT2:
			this.jeu.getLink().setaDansSaMain(1);
			break;
		case DIGIT3:
			this.jeu.getLink().setaDansSaMain(2);
			break;
		case DIGIT4:
			this.jeu.getLink().setaDansSaMain(3);
			break;
		case DIGIT5:
			this.jeu.getLink().setaDansSaMain(4);
			break;
		case DIGIT6:
			this.jeu.getLink().setaDansSaMain(5);
			break;
		default:
			break;
		}
		
	}
	
	

}
