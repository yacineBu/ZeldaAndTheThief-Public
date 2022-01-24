package projetJAY.modele;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;

public class Joueur extends Acteur {

	final int pxIteraction = 20;
	
	private boolean veutPousser;
	
	private boolean veutLancer;
	private boolean empecherRepLancement;		// faut bien sauvegarder quelque part le fait que l'action � �t� appliqu�e : oblig� de cr�er encore des variables booleens
	
	private boolean veutAttaquer;
	private boolean empecherRepAttaque;
	
	private boolean veutInteragir;
	private boolean empecherRepInteraction;
	
	private IntegerProperty pvJoueur;
	private ObservableList<Item> inventaire;		// A la base c'�tait cens� etre un tableau
	private IntegerProperty aDansSaMain;			// indique quel slot de l'inventaire le joueur a actuellement dans sa main
	
	/*
	 * Le joueur d�marre avec une arme de type Baton dans son inventaire
	 */
	public Joueur(int positionX, int positionY, ZoneDeJeu jeu) {
		super(positionX, positionY, jeu, 5);
		this.veutPousser = false;
		this.veutLancer = false;
		this.veutAttaquer = false;
		this.veutInteragir = false;
		
		this.pvJoueur = new SimpleIntegerProperty(100);
		this.inventaire = FXCollections.observableArrayList();
		
		// Cr�ation de 6 slots (dont le 1er occup� par un Baton)
		this.inventaire.add(new Baton(1000, jeu));
		for (int i=0 ; i < 5 ; i++) {
			this.inventaire.add(null);
		}
		
		this.aDansSaMain = new SimpleIntegerProperty(0);
	}
	
	// Getters et Setters des bool�ens
	public boolean isVeutPousser() {
		return veutPousser;
	}

	public void setVeutPousser(boolean veutPousser) {
		this.veutPousser = veutPousser;
	}

	public boolean isVeutLancer() {
		return veutLancer;
	}

	public void setVeutLancer(boolean veutLancer) {
		this.veutLancer = veutLancer;
	}
	
	public boolean isEmpecherRepLancement() {
		return empecherRepLancement;
	}
	
	public void setEmpecherRepLancement(boolean empecherRepLancement) {
		this.empecherRepLancement = empecherRepLancement;
	}

	public boolean isVeutAttaquer() {
		return veutAttaquer;
	}

	public void setVeutAttaquer(boolean veutAttaquer) {
		this.veutAttaquer = veutAttaquer;
	}
	
	public boolean isEmpecherRepAttaque() {
		return empecherRepAttaque;
	}
	
	public void setEmpecherRepAttaque(boolean bloquerRepAttaque) {
		this.empecherRepAttaque = bloquerRepAttaque;
	}

	public boolean isVeutInteragir() {
		return veutInteragir;
	}

	public void setVeutInteragir(boolean veutInteragir) {
		this.veutInteragir = veutInteragir;
	}
	
	public boolean isEmpecherRepInteraction() {
		return empecherRepInteraction;
	}
	
	public void setEmpecherRepInteraction(boolean empecherRepInteraction) {
		this.empecherRepInteraction = empecherRepInteraction;
	}
	
	
	public ObservableList<Item> getIntenvaire() {
		return inventaire;
	}
	
	public IntegerProperty getPvJoueurProperty() {
		return pvJoueur;
	}
	
	public int getPvJoueur() {
		return pvJoueur.getValue();
	}
	
	public void setPvJoueur(int nouveauPv) {
		pvJoueur.setValue(nouveauPv);
	}
	
	public IntegerProperty getaDansSaMainProperty() {
		return aDansSaMain;
	}
	
	public int getaDansSaMain() {
		return aDansSaMain.getValue();
	}
	
	public void setaDansSaMain(int aDansSaMain) {
		this.aDansSaMain.setValue(aDansSaMain);;
	}
	

	public void traiterActionsJoueur() {
		if (veutPousser) {
			System.out.println("veut pousser");
		}
		if (veutLancer && !isEmpecherRepLancement()) {
			System.out.println("veut lancer");
			
			this.empecherRepLancement = true;
		}
		if (veutAttaquer && !isEmpecherRepAttaque()) {
			Item itemDansLaMain = this.inventaire.get(getaDansSaMain());
			if (itemDansLaMain instanceof Arme) {
				attaquer((Arme) itemDansLaMain);
			}
			
			this.empecherRepAttaque = true;
		}
		if (veutInteragir && !isEmpecherRepInteraction()) {
			Item itemProche = itemLePlusProche();
			if (itemProche != null) {
				// Parcours de la liste pour trouver le premier slot vide. Rien ne se passe si tout est rempli
				for (int i=0 ; i<this.inventaire.size() ; i++) {
					if (this.inventaire.get(i) == null) {
						this.inventaire.set(i, itemProche);
						this.getJeu().retirerItemDuJeu(itemProche);
						break;		// sort du for
					}
				}	
			}
			
			this.empecherRepInteraction = true;
		}
	}

	/*
	 * Parcours la liste d'items dans ZoneDeJeu puis retrouve LE PREMIER Item valide (pks y'aura pas 36 items devant le joueur comme 36 ennemis).
	 * Si aucun item ne peut être ramass�, elle renvoit null.
	 */
	private Item itemLePlusProche() {
		double distanceItemRegardeJoueur;
		
		for (Item itemRegarde : this.getJeu().getItems()) {
			distanceItemRegardeJoueur = itemRegarde.distanceComposantJoueur();
			
			if (distanceItemRegardeJoueur < pxIteraction)
				return itemRegarde;
		}
		
		// Si on a trouv� aucun item ramassable, on revoit null
		return null;
	}

	public void attaquer(Arme armeDansLaMain) {
		armeDansLaMain.utiliserArme();
	}

	
}
