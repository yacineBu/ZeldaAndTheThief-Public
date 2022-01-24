package projetJAY.modele;

public class Squelette extends Ennemi {

	final static int tempsMarcheConst = 1000;
	final static int tempsPauseConst = 200;
	final static int cooldown = 500;
	final static int pvEnnemi = 200;
	final static int rayonAttaque = 150;
	
	private int nbrChoisi;


	public Squelette(int positionX, int positionY, ZoneDeJeu jeu) {
		super(positionX, positionY, jeu, cooldown, pvEnnemi, rayonAttaque);
	}
	
	@Override
	public void seDeplaceAleatoirement(int tempsEcoule) {
		
		// Si l'ennemi fait sa pause, on le remmettera en ronde une fois qu'il aura attendu la durée tempsPause
		if (this.getEstEnPause()) {
			if (tempsEcoule - this.getDateDePause() > tempsPauseConst) {
				this.setEstEnPause(false);
				this.setDateDeMarche(tempsEcoule);
				
				// Il choisira ensuite aléatoirement sa direction
				this.nbrChoisi = (int) (Math.random() * 4);
				switch (this.nbrChoisi) {
				case 0: {
					this.setVeutSeDeplacerHaut(true);
					break;
				}		
				case 1: {
					this.setVeutSeDeplacerBas(true);
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
		// Si l'ennemi est en ronde, on le remmettera en pause une fois qu'il aura travaillé la durée tempsMarche
		else {
			if (tempsEcoule - this.getDateDeMarche() > tempsMarcheConst) {
							
				this.setEstEnPause(true);
				this.setDateDePause(tempsEcoule);
				
				// puis on repasse à false la bonne variable
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
	public void attaquer(int tempsEcoule) {
		 	Shuriken shuriken = new Shuriken(this.getPositionX(), this.getPositionY(), getJeu());
		    getJeu().getItems().add(shuriken);
		    
		    this.setDateAttaque(tempsEcoule);
	}

	@Override
	public void agir(int tempsEcoule) {
		if (focusSurLink()) {
			this.setVeutSeDeplacerHaut(false);
		 	this.setVeutSeDeplacerBas(false);
		 	this.setVeutSeDeplacerDroite(false);
		 	this.setVeutSeDeplacerGauche(false);
		 	
		 	if (peutReattaquer(tempsEcoule))
		 		attaquer(tempsEcoule);
		}
		else {
			seDeplaceAleatoirement(tempsEcoule);
		}
	}
	
	
	

}
