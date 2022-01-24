package projetJAY.vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.Pane;
import projetJAY.modele.Terrain;

public class TerrainVue {
	
	private Terrain terrain;
	
	private Pane Pane;
	
    private TilePane tilePaneObstacle;
	
	
	public TerrainVue(Terrain terrain, Pane pane, TilePane tilePaneObstacle) {
		this.terrain = terrain;
		this.Pane = pane;
		this.tilePaneObstacle = tilePaneObstacle;
	}



	public void afficherTerrain() {
		Image img = new Image("file:src/projetJAY/ressources/TileSetFinale.png");
		ImageView iV = null;		

		int [] codeTuiles = this.terrain.getCodeTuiles();
		TilePane tpTemp = new TilePane();
		tpTemp.setPrefWidth(2432);
//		tpTemp.setPrefHeight(480);
		for(int i = 0;i<codeTuiles.length;i++) {
			
			int x = (codeTuiles[i]%125)*16;
			int y = (codeTuiles[i]/125)*16;
			
			iV = new ImageView(img);
			Rectangle2D tuile = new Rectangle2D(x,y,16,16);
			iV.setViewport(tuile);
			
			tpTemp.getChildren().add(iV);
		}
		this.Pane.getChildren().add(tpTemp);
		
		
		int [] codeTuilesObstacle = this.terrain.getCodeTuilesObstacle();
		this.tilePaneObstacle.setPrefWidth(2432);
		//this.tilePaneObstacle.setPrefHeight(480);
		for (int i = 0; i < codeTuilesObstacle.length; i++) {

			int x = (codeTuilesObstacle[i] % 125) * 16;
			int y = (codeTuilesObstacle[i] / 125) * 16;

			iV = new ImageView(img);
			Rectangle2D tuile = new Rectangle2D(x, y, 16, 16);
			iV.setViewport(tuile);

			this.tilePaneObstacle.getChildren().add(iV);
		}
		
		this.Pane.getChildren().add(tilePaneObstacle);

		
		int [] codeTuilesDeco = this.terrain.getCodeTuilesDeco();
		tpTemp = new TilePane();
		tpTemp.setPrefWidth(2432);
		//tpTemp.setPrefHeight(480);
		for (int i = 0; i < codeTuilesDeco.length; i++) {

			int x = (codeTuilesDeco[i] % 125) * 16;
			int y = (codeTuilesDeco[i] / 125) * 16;

			iV = new ImageView(img);
			Rectangle2D tuile = new Rectangle2D(x, y, 16, 16);
			iV.setViewport(tuile);

			tpTemp.getChildren().add(iV);
		}
		this.Pane.getChildren().add(tpTemp);
	}
}
