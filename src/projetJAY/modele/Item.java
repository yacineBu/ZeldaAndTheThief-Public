package projetJAY.modele;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public abstract class Item extends Composant {

	public Item(int positionX, int positionY, ZoneDeJeu jeu) {
		super(positionX, positionY, jeu);
	}
	
	public Item(ZoneDeJeu jeu) {
		super(jeu);
	}
	
	/*
	 * Retourne le slot de l'inventaire o� est stock� l'Item. Renvoie -1 si il n'est pas dans l'inventaire.
	 */
	public int seTrouveAuSlot(ObservableList<Item> intenvaireJoueur) {
		String idItemRecherche = this.getIdComposant();
		Item itemRegarde;
		
		for (int i=0 ; i<intenvaireJoueur.size() ; i++) {
			itemRegarde = intenvaireJoueur.get(i);
			
			if (itemRegarde != null) {
				if (itemRegarde.getIdComposant().equals(idItemRecherche)) {
					return i;
				}
			}
		}
		
		return -1;
	}

}
