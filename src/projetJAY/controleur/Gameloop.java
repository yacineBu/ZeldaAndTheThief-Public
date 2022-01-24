package projetJAY.controleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import projetJAY.modele.Acteur;
import projetJAY.modele.ActeurIA;
import projetJAY.modele.Joueur;
import projetJAY.modele.ZoneDeJeu;

public class Gameloop implements EventHandler<ActionEvent> {

	private ZoneDeJeu jeu;
	private int temps;		// Repr�sente des millisecondes
	
	public Gameloop(ZoneDeJeu jeu) {
		this.jeu = jeu;
		this.temps = 0;
	}

	/*
	 * La m�thode qui fait tourner le jeu. Elle est appel�e environ 50 fois par seconde (+ le temps d'execution de la m�thode).
	 */
	@Override
	public void handle(ActionEvent arg0) {
//		 System.out.println(this.jeu.getLink().getaDansSaMain());
		
		// Le mod�le s'occupe de faire �voluer le jeu
		jeu.evolution(this.temps);

		// On fait �voluer le temps
		this.temps += 20;
	}
}

