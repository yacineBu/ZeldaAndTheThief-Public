package projetJAY.controleur;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import projetJAY.modele.ZoneDeJeu;

public class ControleurKeyReleased implements EventHandler<KeyEvent> {
	
	private ZoneDeJeu jeu;
	
	public ControleurKeyReleased(ZoneDeJeu jeu) {
		this.jeu = jeu;
	}

	@Override
	public void handle(KeyEvent event) {
		
		switch (event.getCode()) {
		case Z:
			this.jeu.getLink().setVeutSeDeplacerHaut(false);
			break;
		case S:
			this.jeu.getLink().setVeutSeDeplacerBas(false);
			break;
		case Q:
			this.jeu.getLink().setVeutSeDeplacerGauche(false);
			break;
		case D:
			this.jeu.getLink().setVeutSeDeplacerDroite(false);
			break;
		case NUMPAD5:
			this.jeu.getLink().setVeutPousser(false);
			break;
		case NUMPAD8:
			this.jeu.getLink().setVeutLancer(false);
			this.jeu.getLink().setEmpecherRepLancement(false);
			break;
		case NUMPAD7:
			this.jeu.getLink().setVeutAttaquer(false);
			this.jeu.getLink().setEmpecherRepAttaque(false);
			break;
		case NUMPAD4:
			this.jeu.getLink().setVeutInteragir(false);
			this.jeu.getLink().setEmpecherRepInteraction(false);
			break;
			
		default:
			break;
		}
		
		/* Optimisation à faire plus tard :

		// Si le joueur ne se d�place dans aucune direction...
		if (!this.jeu.getLink().isDeplacementHaut() && !this.jeu.getLink().isDeplacementBas() && !this.jeu.getLink().isDeplacementGauche() && !this.jeu.getLink().isDeplacementDroite()) {
			AnimationTimer deplacementEnContinu = VitrineDeVariable.getDeplacementEnContinu();
			deplacementEnContinu.stop();		// ...alors on arr�te le AnimationTimer pour �conomiser des ressources
		}
		
		*/
		
	}

}
