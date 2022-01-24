package projetJAY.vue;

import javafx.beans.value.ChangeListener;
import javafx.collections.ArrayChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import projetJAY.modele.Arc;
import projetJAY.modele.Baton;
import projetJAY.modele.Composant;
import projetJAY.modele.Item;
import projetJAY.modele.ZoneDeJeu;

public class InventaireVue {
	
	private HBox inventaire;
	private Pane paneJeu;
	private Rectangle sampleCasesInventaire;
	private ZoneDeJeu jeu;
//	private Rectangle indicateurCree;
	
	public InventaireVue(HBox inventaire, Pane paneJeu, ZoneDeJeu jeu) {
		this.inventaire = inventaire;
		this.paneJeu = paneJeu;
		this.jeu = jeu;
		this.sampleCasesInventaire = ((Rectangle) inventaire.getChildren().get(0));
	}
	
	public void creeIndicateur() {
		// D�finition des propri�tes g�n�rales de carreIndicateur
		Rectangle carreIndicateur = new Rectangle();
		carreIndicateur.setWidth(this.sampleCasesInventaire.getWidth() * 0.85);
		carreIndicateur.setHeight(this.sampleCasesInventaire.getHeight() * 0.85);
		carreIndicateur.setFill(Color.web("#908d8d"));
		carreIndicateur.setOpacity(0.6);
		
		// On place carreIndicateur au niveau du 1er slot de l'inventaire (correspond au mod�le)
		this.placerAuSlot0(carreIndicateur);
		
		// On rend visible carreIndicateur
		paneJeu.getChildren().add(carreIndicateur);

		// Pour afficher les Items cr�es dans l'inventaire pendant l'initialisation du joueur, on parcours son inventaire (Pour l'instant y'a que le Baton)
		for (Item itemRegarde : this.jeu.getLink().getIntenvaire()) {
			if (itemRegarde instanceof Baton) {
				Image chargementImageBaton = new Image("file:src/projetJAY/ressources/batonInv.png");
				ImageView imageBaton = new ImageView(chargementImageBaton);
				imageBaton.toFront();
				
				// Correspond �galement au mod�le
				this.placerAuSlot0(imageBaton);
				
				this.paneJeu.getChildren().add(imageBaton);
			}
			
			if (itemRegarde instanceof Arc) {
				Image chargementImageArc = new Image("file:src/projetJAY/ressources/ArcInv.png");
				ImageView imageArc = new ImageView(chargementImageArc);
				imageArc.toFront();
				
				// Correspond �galement au mod�le
				this.placerAuSlot0(imageArc);
				
				this.paneJeu.getChildren().add(imageArc);
				
			}
			
		}
		
		
		// On termine par mettre en place les 2 listeners :
		// Un 1er pour g�rer les Items dans l'inventaire (position dans l'inventaire) 
		// et un 2nd pour g�rer la position du carreIndicateur en fnc de la valeur aDansSaMain
		ListChangeListener<Item> ecouteurItemsInventaire = ((javafx.collections.ListChangeListener.Change<? extends Item> nouveauInventaire) -> {		
			while(nouveauInventaire.next()) {
				for (Item itemRegarde : nouveauInventaire.getAddedSubList()) {
					if (itemRegarde != null) {
						Image chargementImageItem;
						ImageView imageItem = null;

						if (itemRegarde instanceof Baton) {
							chargementImageItem = new Image("file:src/projetJAY/ressources/batonInv.png");
							imageItem = new ImageView(chargementImageItem);
						}
						
						if (itemRegarde instanceof Arc) {
							chargementImageItem = new Image("file:src/projetJay/ressources/ArcInv.png");
							imageItem = new ImageView(chargementImageItem);
						}

						// On regarde dans quel slot placer imageItem en fnc de sa position dans
						// l'inventaire du mod�le
						switch (itemRegarde.seTrouveAuSlot(this.jeu.getLink().getIntenvaire())) {
						case 0:
							placerAuSlot0(imageItem);
							break;
						case 1:
							placerAuSlot1(imageItem);
							break;
						case 2:
							placerAuSlot2(imageItem);
							break;
						case 3:
							placerAuSlot3(imageItem);
							break;
						case 4:
							placerAuSlot4(imageItem);
							break;
						case 5:
							placerAuSlot5(imageItem);
							break;
						default:
							System.out.println(
									"Erreur : Item non-pr�sent dans l'inventaire (Lancement exception � l'avenir)");
							break;
						}

						this.paneJeu.getChildren().add(imageItem);
					}
				}
			}
		});
		this.jeu.getLink().getIntenvaire().addListener(ecouteurItemsInventaire);
		
		ChangeListener<Number> ecouteuraDansSaMain = ((obs, ancien, nouveau) -> {
			switch ((int) nouveau) {
			case 0:
				this.placerAuSlot0(carreIndicateur);
				break;
			case 1:
				this.placerAuSlot1(carreIndicateur);
				break;
			case 2:
				this.placerAuSlot2(carreIndicateur);
				break;
			case 3:
				this.placerAuSlot3(carreIndicateur);
				break;
			case 4:
				this.placerAuSlot4(carreIndicateur);
				break;
			case 5:
				this.placerAuSlot5(carreIndicateur);
				break;
				
			default:
				break;
			}
		});
		this.jeu.getLink().getaDansSaMainProperty().addListener(ecouteuraDansSaMain);
	}
	
	private void placerAuSlot0(Rectangle c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add(( this.sampleCasesInventaire.getWidth() - c.getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add(( this.sampleCasesInventaire.getHeight() - c.getHeight()) / 2));
	}
	
	private void placerAuSlot0(ImageView c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add(( this.sampleCasesInventaire.getWidth() - c.getImage().getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add(( this.sampleCasesInventaire.getHeight() - c.getImage().getHeight()) / 2));
	}
	
	private void placerAuSlot1(Rectangle c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( (this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getHeight()) / 2));
	}
	
	private void placerAuSlot1(ImageView c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( (this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getImage().getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getImage().getHeight()) / 2));
	}
	
	private void placerAuSlot2(Rectangle c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 2*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getHeight()) / 2));
	}
	
	private void placerAuSlot2(ImageView c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 2*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getImage().getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getImage().getHeight()) / 2));
	}
	
	private void placerAuSlot3(Rectangle c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 3*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getHeight()) / 2));
	}
	
	private void placerAuSlot3(ImageView c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 3*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getImage().getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getImage().getHeight()) / 2));
	}
	
	private void placerAuSlot4(Rectangle c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 4*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getHeight()) / 2));
	}
	
	private void placerAuSlot4(ImageView c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 4*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getImage().getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getImage().getHeight()) / 2));
	}
	
	private void placerAuSlot5(Rectangle c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 5*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getHeight()) / 2));
	}
	
	private void placerAuSlot5(ImageView c) {
		c.translateXProperty().bind(inventaire.layoutXProperty().add( 5*(this.sampleCasesInventaire.getWidth() + this.inventaire.getSpacing()) + ( this.sampleCasesInventaire.getWidth() - c.getImage().getWidth()) /2));
		c.translateYProperty().bind(inventaire.layoutYProperty().add((this.sampleCasesInventaire.getHeight() - c.getImage().getHeight()) / 2));
	}
}
