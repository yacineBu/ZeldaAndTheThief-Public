package projetJAY;

import java.util.ArrayList;

import projetJAY.modele.*;

public class MainTemporaire {

	public static void main(String[] args) {
		int[]b;
		
		b = Terrain.tableauFichierCSV("src/projetJAY/ressources/MapFinaleZelda_obstacle.csv");
		
//		ArrayList<Integer> a = new ArrayList<Integer>();
//		
//		for(int i = 0; i < b.length; i++) {
//			if(a.contains(b[i]) == false) {
//				a.add(b[i]);
//			}
//		}
		System.out.println(b[7264]);
	}

}
