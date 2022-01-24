package projetJAY.modele;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ZoneDeJeu {
	
	final int spawnJoueurX = 500;
	final int spawnJoueurY = 500;
	
	private Terrain map;
	private Joueur link;

	private Collision collisionsDesActeurs;				// La classe qui gï¿½re les collisions
	
	private ObservableList<ActeurIA> acteursIA;			// Une liste qui contient tous les acteurs (sauf le joueur) du modï¿½le. Le choix de faire cette liste est liï¿½e ï¿½ la mï¿½thode evolution().
	private ObservableList<Item> items;
	
	public ZoneDeJeu() {
		
		this.map = new Terrain();
		this.link = new Joueur(spawnJoueurX, spawnJoueurY, this);
		this.collisionsDesActeurs = new Collision(this.map);
		
		this.acteursIA = FXCollections.observableArrayList();
		this.items = FXCollections.observableArrayList();
	}
	
	public Joueur getLink() {
		return link;
	}
	
	public Terrain getMap() {
		return map;
	}

	public Collision getCollisionsDesActeurs() {
		return collisionsDesActeurs;
	}
	
	public ObservableList<ActeurIA> getActeursIA() {
		return acteursIA;
	}
	
	public ObservableList<Item> getItems() {
		return items;
	}
	
	public void creerActeursDansLaPartie() {
		Ennemi ennemi1 = new Gluant(450, 400, this);
		Ennemi ennemi2 =  new Squelette(300, 302, this);
		Ennemi BossFinal = new BossFinal(1925,335, this);
		this.acteursIA.addAll(ennemi1, ennemi2, BossFinal);
	}
	
	public void creeItemsDuJeu() {
		this.items.add(new Baton(705, 335, 1000, this));
		this.items.add(new Arc(505,335, 1000, this));
	}
	
	public void retirerItemDuJeu(Item itemProche) {
		this.getItems().remove(itemProche);
	}
	
	public void creerShurikenDansLaPartie(Shuriken e) {
		this.items.add(e);
	}
	
	public void evolution(int tempsEcoule) {		
		// On traite les souhaits de dï¿½placements du joueur
		try {
			this.link.traiterDeplacement();
		} catch(Exception e) {
			respawnJoueur();
		}

		// Idem pour tous les acteursIA, aprï¿½s leur avoir appliquï¿½ leur mï¿½thode de
		// dï¿½placement
		for (int i=0 ; i<acteursIA.size() ; i++) {
			
			ActeurIA acteurRegarde = this.acteursIA.get(i);
			acteurRegarde.agir(tempsEcoule);

			try {
				acteurRegarde.traiterDeplacement();
			} catch (Exception e) {
				replacerActeur(acteurRegarde);
			}

			if(acteurRegarde instanceof Ennemi && ((Ennemi) acteurRegarde).estMort()) {
				this.acteursIA.remove(acteurRegarde);
			}
			else {
				acteurRegarde.seDeplaceAleatoirement(tempsEcoule);
			}

		}

		// On traite les souhaits d'action du joueur
		this.link.traiterActionsJoueur();
		
		// On fait déplacer les items lancable du jeu
		for (int i=0 ; i<items.size() ; i++) { /* L'idéale aurait été de parcourir une liste d'ItemLancable, mais trop de modifs nécessaire*/
			Item itemRegarde = this.items.get(i);
			if (itemRegarde instanceof ItemLancable) {
				ItemLancable itemLancableRegarde = (ItemLancable) itemRegarde;
				itemLancableRegarde.seDeplace();
				
				if (itemLancableRegarde.estUsee())
					items.remove(itemLancableRegarde);
			}
		}
		
	}

	private void respawnJoueur() {
		this.link.setPositionX(spawnJoueurX);
		this.link.setPositionY(spawnJoueurY);
	}
	
	private void replacerActeur(Acteur a) {
		// Traitement de positionX
		if (a.getPositionX() < 0) {
			a.setPositionX(20);
		}
		else if (a.getPositionX() > Terrain.tailleDesTuiles * Terrain.nbTuilesParLigne /*Soit 1920*/) {
			a.setPositionX(Terrain.tailleDesTuiles * Terrain.nbTuilesParLigne - 20);
		}
		
		// Traitement de positionY
		if (a.getPositionY() < 0) {
			a.setPositionY(20);
		}
		else if (a.getPositionY() > Terrain.tailleDesTuiles * Terrain.nbTuilesParColonne /*Soit 1440*/) {
			a.setPositionY(Terrain.tailleDesTuiles * Terrain.nbTuilesParColonne - 20);
		}
	}
	
	
	
}
