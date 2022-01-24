package projetJAY.modele;

import java.io.*;

public class Terrain {
	/*
	 * LES PARAMETRES DU TERRAIN CHARGEE
	 * (changer ces constantes si on change la map)
	 * (A l'avenir, on nous obligera peut-etre de les définir automatiquement après avoir lu le json)
	 */

	final static int nbTuilesParLigne = 152;
	final static int nbTuilesParColonne = 122;
	final static int tailleDesTuiles = 16;		// la taille en pixel du coté d'une tuille (tuille = carré)


	
	public static int[] tableauFichierCSV(String t){
		File map = new File(t);
		try {
			FileReader fileReader = new FileReader(map);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int []tableauMap = new int[18544];
			int p = 0;
			for(int i = 0;i < 122; i++) {
				String line = bufferedReader.readLine();
				for(int j = 0; j < 152; j++) {
					String[] parts = line.split(",");
					String part = parts[j];
					tableauMap[j+p]= Integer.parseInt(part);
				}
				p += 152;
			}
			bufferedReader.close();
			return tableauMap;
		} catch(FileNotFoundException e) {
			System.err.printf("le fichier est pas trouvé "+ map.toString());
		} catch(IOException e) {
			System.err.printf("la ligne peut pas etre lu "+ map.toString());
		}
		return null;
	}

	private int [] codeTuilesHerbe = tableauFichierCSV("src/projetJAY/ressources/MapFinaleZelda_Herbe.csv");
	
	public int[] getCodeTuiles() {
		return this.codeTuilesHerbe;
	}
	
	private int [] codeTuilesObstacle = tableauFichierCSV("src/projetJAY/ressources/MapFinaleZelda_obstacle.csv");
	
	public int[] getCodeTuilesObstacle() {
		return this.codeTuilesObstacle;
	}

	private int [] codeTuilesDeco = tableauFichierCSV("src/projetJAY/ressources/MapFinaleZelda_deco.csv");

	public int[] getCodeTuilesDeco() {
		return this.codeTuilesDeco;
	}
}
