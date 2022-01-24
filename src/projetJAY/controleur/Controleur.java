package projetJAY.controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import projetJAY.modele.Acteur;
import projetJAY.modele.Arc;
import projetJAY.modele.Baton;
import projetJAY.modele.BossFinal;
import projetJAY.modele.Collision;
import projetJAY.modele.Composant;
import projetJAY.modele.Ennemi;
import projetJAY.modele.Fleche;
import projetJAY.modele.Gluant;
import projetJAY.modele.Joueur;
import projetJAY.modele.Shuriken;
import projetJAY.modele.Squelette;
import projetJAY.modele.Terrain;
import projetJAY.modele.ZoneDeJeu;
import projetJAY.vue.ArcVue;
import projetJAY.vue.BatonVue;
import projetJAY.vue.FlecheVue;
import projetJAY.vue.BossFinalVue;
import projetJAY.vue.GluantVue;
import projetJAY.vue.JoueurVue;
import projetJAY.vue.ShurikenVue;
import projetJAY.vue.SqueletteVue;
import projetJAY.vue.InventaireVue;
import projetJAY.vue.TerrainVue;

public class Controleur implements Initializable {
	
    @FXML
    private Pane paneJeu;			// Le Pane principale
    
    @FXML
    private HBox inventaire;
	
    private TilePane tilePaneObstacle;
	
    private Label labelNbrPv;
    
    private Label labelPv;
	
	private ZoneDeJeu jeu;
	
	private Timeline gameLoop;		// Un objet qui permet de definir les animations
	
	@FXML
	private ImageView linkDeLaVue;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// CREATION DU JEU : SUR jeu SERONT FAITES TOUTES LES MODIFS SUR LE MODELE
		this.jeu = new ZoneDeJeu();
		
		// Cr�ation de la map
		this.tilePaneObstacle = new TilePane();
		TerrainVue terrain = new TerrainVue(this.jeu.getMap(), paneJeu, tilePaneObstacle);
		terrain.afficherTerrain();
		
		// Tout de suite apr�s on rempli l'attribut jeu pour les 2 controlleurs : ControleurKeyPressed et de ControleurKeyReleased
		initCommandesDuJoueur();
		
		// Listeners des listes (surveille les changements de toutes les listes du mod�le)
		initListenerDesListes();
		
		this.jeu.creeItemsDuJeu();
		this.jeu.creerActeursDansLaPartie();		// !!!!!!!!!!!!!!!!!!!!!!!!
		
		// Listener pour le joueur
		initListenerDuJoueur(this.jeu.getLink());
		
		// Mise en place de la camera (scrolling map)

		paneJeu.layoutXProperty().bind(this.jeu.getLink().getPositionXProperty().multiply(-1).add(240)); // on multiplie par -1 pour inverser les directions du d�filement 
		paneJeu.layoutYProperty().bind(this.jeu.getLink().getPositionYProperty().multiply(-1).add(240)); // on ajoute des pixels pour centrer la cam�ra

		
		// Initilalisation et lancement de la gameloop
		initAnimation();
		gameLoop.play();
		
		initInterfaceJoueur();	
	}

	private void initInterfaceJoueur() {
		this.labelNbrPv = new Label();
		this.labelPv = new Label("PV : ");
		this.paneJeu.getChildren().add(labelNbrPv);
		this.paneJeu.getChildren().add(labelPv);
		
		this.labelNbrPv.layoutXProperty().bind(this.jeu.getLink().getPositionXProperty().add(-195));
		this.labelNbrPv.layoutYProperty().bind(this.jeu.getLink().getPositionYProperty().add(-239));
		this.labelNbrPv.textProperty().bind(jeu.getLink().getPvJoueurProperty().asString());
		
		this.labelPv.layoutXProperty().bind(this.jeu.getLink().getPositionXProperty().add(-230));
		this.labelPv.layoutYProperty().bind(this.jeu.getLink().getPositionYProperty().add(-240));
		
		this.inventaire.layoutXProperty().bind(this.jeu.getLink().getPositionXProperty().add(-20));
		this.inventaire.layoutYProperty().bind(this.jeu.getLink().getPositionYProperty().add(-230));
		this.inventaire.toFront();
		
		InventaireVue selectionInventaire = new InventaireVue(inventaire, paneJeu, jeu);
		selectionInventaire.creeIndicateur();
	}
	
	private void initCommandesDuJoueur() {
		ControleurKeyPressed c1 = new ControleurKeyPressed(this.jeu, linkDeLaVue);
		ControleurKeyReleased c2 = new ControleurKeyReleased(this.jeu);
		paneJeu.addEventHandler(KeyEvent.KEY_PRESSED, c1);
		paneJeu.addEventHandler(KeyEvent.KEY_RELEASED, c2);
	}
	
	private void initListenerDesListes() {	
		ListChangeListener<Composant> ecouteurDesComposantsDuJeu = ((javafx.collections.ListChangeListener.Change<? extends Composant> nouvelleListeDeComposant) -> {
			while (nouvelleListeDeComposant.next()) {
				for (Composant nouveauComposant : nouvelleListeDeComposant.getAddedSubList()) {
					if (nouveauComposant instanceof Gluant) {
						GluantVue ennemiSurLaVue = new GluantVue((Gluant) nouveauComposant, this.paneJeu);
						ennemiSurLaVue.creerEnnemi();
					}
					

					if (nouveauComposant instanceof Baton) {
						BatonVue batonSurLaVue = new BatonVue(paneJeu, (Baton) nouveauComposant);
						batonSurLaVue.creeBaton();
					}
					
					if (nouveauComposant instanceof Arc) {
						ArcVue ArcSurLaVue = new ArcVue(paneJeu, (Arc) nouveauComposant);
						ArcSurLaVue.creeArc();
					}
					
					if (nouveauComposant instanceof Fleche) {
						FlecheVue FlecheSurLaVue = new FlecheVue(paneJeu, (Fleche) nouveauComposant);
						FlecheSurLaVue.creeFleche();
					}
					if (nouveauComposant instanceof Squelette) {
						SqueletteVue ennemiSurLaVue2 = new SqueletteVue(nouveauComposant, this.paneJeu);
						ennemiSurLaVue2.creerEnnemi();
					}
					if (nouveauComposant instanceof Shuriken) {
						ShurikenVue itemSurLaVue = new ShurikenVue(nouveauComposant, this.paneJeu);
						itemSurLaVue.creerShuriken();
					}	
					if (nouveauComposant instanceof BossFinal) {
						BossFinalVue ennemiSurLaVue = new BossFinalVue(nouveauComposant, this.paneJeu);
						ennemiSurLaVue.creerEnnemi();

						ennemiSurLaVue.getEnnemiCree().translateXProperty().bind(nouveauComposant.getPositionXProperty());
						ennemiSurLaVue.getEnnemiCree().translateYProperty().bind(nouveauComposant.getPositionYProperty());
					}
				}

				for (Composant nouveauComposant : nouvelleListeDeComposant.getRemoved()) {
					this.paneJeu.getChildren().remove(this.paneJeu.lookup("#" + nouveauComposant.getIdComposant()));

				}
			}
		});
		this.jeu.getItems().addListener(ecouteurDesComposantsDuJeu);
		this.jeu.getActeursIA().addListener(ecouteurDesComposantsDuJeu);
		this.jeu.getItems().addListener(ecouteurDesComposantsDuJeu);
		// getItem.addListener
		// getElementsPossables.addListener
		// ... etc		pour toutes les listes de ZoneDejeu
	}
	
	private void initListenerDuJoueur(Joueur link) {
		// On cr�e le joueur au niveau de la vue (comme on peut � l'avenir avoir besoin
		// de manipuler le joueur dans le contoleur, il est cr�e de cette fa�on)
		//linkDeLaVue = new JoueurVue(this.jeu.getLink(), this.paneJeu);
		//linkDeLaVue.creerJoueur();
		
		// puis on lie le joueur de la vue au joueur du mod�le
		linkDeLaVue.toFront();
		linkDeLaVue.translateXProperty().bind(link.getPositionXProperty());
		linkDeLaVue.translateYProperty().bind(link.getPositionYProperty());

		/*linkDeLaVue.getJoueurCree().translateXProperty().bind(link.getPositionXProperty());
		linkDeLaVue.getJoueurCree().translateYProperty().bind(link.getPositionYProperty());*/
	}
	
	private void initAnimation() {
		this.gameLoop = new Timeline();
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame kf = new KeyFrame(Duration.seconds(0.02), new Gameloop(this.jeu));
		this.gameLoop.getKeyFrames().add(kf);		// 1/50 -> 50 images par seconde
													// (on a choisi cette fr�quence de raffraichissement pour pouvoir faire fonctionner notre attribut temps) 
	}



	
	
	/*
	 * Pas de m�thode de rafraichissement dans le controleur. Ce sont les Properties
	 * et les Listeners qui se chargeront de ce travail (Propertie : surveille le
	 * mod�le et pr�vient les abonn�es ; Listner : change la vue par les nouvelles
	 * infos)
	 */
}
