package projetJAY.controleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import projetJAY.modele.Acteur;
import projetJAY.modele.ActeurIA;
import projetJAY.modele.Joueur;
import projetJAY.modele.ZoneDeJeu;

public class Gameloop implements EventHandler<ActionEvent> {

	private ZoneDeJeu jeu;
	private int temps;		// Reprï¿½sente des millisecondes
	
	public Gameloop(ZoneDeJeu jeu) {
		this.jeu = jeu;
		this.temps = 0;
	}

	/*
	 * La mï¿½thode qui fait tourner le jeu. Elle est appelï¿½e environ 50 fois par seconde (+ le temps d'execution de la mï¿½thode).
	 */
	@Override
	public void handle(ActionEvent arg0) {
//		 System.out.println(this.jeu.getLink().getaDansSaMain());
		
		// Le modèle s'occupe de faire évoluer le jeu
		jeu.evolution(this.temps);

		// On fait ï¿½voluer le temps
		this.temps += 20;
	}
}

