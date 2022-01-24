package projetJAY.modele;

import java.util.Timer;

public class BossFinal extends Ennemi{

	final int tempsMarche = 800;
	final int tempsPause = 300;
	
	private boolean estEnPause;
	private int dateDePause;
	private int dateDeMarche;
	private int nbrChoisi;
	
	public BossFinal(int positionX, int positionY, ZoneDeJeu jeu) {
		super(positionX, positionY,jeu,3000,150,200);
		
		this.estEnPause = true;
		this.dateDePause = 0;
	}
	
	@Override
	public void seDeplaceAleatoirement(int tempsEcoule) {
		if (this.estEnPause) {
			if (tempsEcoule - this.dateDePause > this.tempsPause) {
				this.estEnPause = false;
				this.dateDeMarche = tempsEcoule;

				// Il choisira ensuite al�atoirement sa direction
				this.nbrChoisi = (int) (Math.random() * 4);
				switch (this.nbrChoisi) {
					case 0: {
						this.setVeutSeDeplacerHaut(true);
						break;
					}
					case 1: {
						if(this.getPositionY() < 650) {
							this.setVeutSeDeplacerBas(true);
						}
						break;
					}
					case 2: {
						this.setVeutSeDeplacerGauche(true);
						break;
					}
					case 3: {
						this.setVeutSeDeplacerDroite(true);
						break;
					}
				}
			}

		}
		// Si l'ennemi est en ronde, on le remmettera en pause une fois qu'il aura
		// travaill� la dur�e tempsMarche
		else {
			if (tempsEcoule - this.dateDeMarche > this.tempsMarche) {

				this.estEnPause = true;
				this.dateDePause = tempsEcoule;

				// puis on repasse � false la bonne variable
				switch (this.nbrChoisi) {
					case 0: {
						this.setVeutSeDeplacerHaut(false);
						break;
					}
					case 1: {
						this.setVeutSeDeplacerBas(false);
						break;
					}
					case 2: {
						this.setVeutSeDeplacerGauche(false);
						break;
					}
					case 3: {
						this.setVeutSeDeplacerDroite(false);
						break;
					}
				}
			}
		}
	}

	@Override
	public void agir(int tempsEcoule) {
		if (focusSurLink()) {
			
			seDeplaceVersLink();
			System.out.println("Boss Finale se dirige vers Link");
			// algo pour aller vers link	(on n'utilise pas seDeplaceAleatoirement(), mais le bfs wish)
		 	
		 	if (peutReattaquer (tempsEcoule) && this.distanceComposantJoueur() < 10)
		 		attaquer(tempsEcoule);
		}
		else {
			seDeplaceAleatoirement(tempsEcoule);
		}
		
	}

	@Override
	public void attaquer(int tempsEcoule) {
		// && this.getPositionX() == getJeu().getLink().getPositionX() && this.getPositionY() == getJeu().getLink().getPositionY()
			getJeu().getLink().setPvJoueur(getJeu().getLink().getPvJoueur()-50);
			this.setDateAttaque(tempsEcoule);

	}

}
