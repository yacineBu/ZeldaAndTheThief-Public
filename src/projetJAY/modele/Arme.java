package projetJAY.modele;

public abstract class Arme extends Item {
	
	private int ptAttaque;
	private int pxDePortee;
	private int tempsCooldown;		// en lien avec l'attibut temps de la gameloop (en ms du coup)


	public Arme(int positionX, int positionY, int tempsCooldown, ZoneDeJeu jeu) {
		super(positionX, positionY, jeu);
		this.tempsCooldown = tempsCooldown;
	}
	
	public Arme(int tempsCooldown, ZoneDeJeu jeu) {
		super(jeu);
		this.tempsCooldown = tempsCooldown;
	}
	
	public int getPxDePortee() {
		return pxDePortee;
	}
	
	public void setPxDePortee(int pxDePortee) {
		this.pxDePortee = pxDePortee;
	}
	
	public int getPtAttaque() {
		return ptAttaque;
	}
	
	public void setPtAttaque(int ptAttaque) {
		this.ptAttaque = ptAttaque;
	}
	
	public int getTempsCooldown() {
		return tempsCooldown;
	}
	
	public void setTempsCooldown(int tempsCooldown) {
		this.tempsCooldown = tempsCooldown;
	}

	public abstract void utiliserArme();


}
