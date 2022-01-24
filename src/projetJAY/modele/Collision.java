package projetJAY.modele;

public class Collision {

	private Terrain tuilesDeLaMap;

	private int[] tuilesInfranchissables = {523, 802, 803, 878, 2005, 2006, 5793, 5794, 5795, 5796, 1516, 2130, 2131, 5918, 5919, 5920, 5921, 56, 57, 6043, 6044, 6045, 6046, 4377, 58, 59, 6168, 6169, 6170, 6171, 6293, 6294, 6295, 6296, 6418, 6419, 6420, 6421, 6, 7, 8, 9, 10, 2127, 2129, 2128, 129, 130, 878, 131, 132, 133, 134, 135, 2254, 1000, 254, 255, 3, 256, 257, 258, 259, 260, 183, 381, 382, 383, 384, 385, 3883, 3884, 506, 507, 508, 509, 510, 2252, 2253, 4008, 4009, 4133, 4134, 5140, 3896, 1003, 1004, 653, 654, 655, 406, 407, 531, 532, 683, 4161, 181, 182, 2625, 2626, 2627, 4159, 2750, 2751, 2752, 23, 4284, 10188, 10189, 10190, 10191, 10192, 5927, 5928, 5929, 5930, 10418, 10419, 10420, 10421, 10422, 2875, 2876, 2877, 273, 3006, 10313, 10314, 10315, 10316, 10317, 6052, 6053, 6054, 6055, 10543, 10544, 10545, 10546, 10547, 3000, 3001, 3002, 3131, 10438, 10439, 10440, 10441, 10442, 6177, 6178, 6179, 6180, 10668, 10669, 10670, 10671, 10672, 3125, 3126, 3127, 2513, 2514, 10563, 10564, 10565, 10566, 10567, 6302, 6303, 6304, 6305, 10793, 10794, 10795, 10796, 10797, 3250, 3251, 3252, 2638, 2639, 10688, 10689, 10690, 10691, 10692, 6427, 6428, 6429, 6430, 10918, 10919, 10920, 10921, 10922, 3375, 3376, 3377, 3500, 3501, 3502, 755, 757, 766, 880, 882, 1264, 1153, 1154, 1155, 1278, 1279, 1280, 1403, 1404, 1405, 891, 1005, 1007, 306, 307, 431, 432, 1016, 556, 557, 768, 769, 770, 893, 894, 895, 1018, 1019, 1020, 1268, 1269, 1267, 1626, 1627, 1628, 1895, 1751, 638, 639, 1753, 160, 157, 6563, 763, 764, 283, 282, 4500, 4501, 4502, 4503, 4504, 4505, 427, 428, 888, 889, 5506, 5507, 5508, 5509, 5510, 5511, 5512, 5513, 5514, 5515, 5516, 5517, 5518, 5519, 5520, 5521, 4526, 4527, 4528, 4529, 4530, 4531, 4532, 4533, 4534, 4535, 4536, 4537, 4625, 4626, 4627, 4628, 4629, 4630, 552, 553, 5631, 5632, 5633, 5634, 5635, 5636, 5637, 5638, 5639, 5640, 5641, 5642, 5643, 5644, 5645, 5646, 4651, 4652, 4653, 4654, 4655, 4656, 4657, 4658, 4659, 4660, 4661, 4662, 4750, 4751, 4752, 4753, 4754, 4755, 2768, 2769, 2770, 2771, 2772, 5756, 5757, 5758, 5759, 5760, 5761, 5762, 5763, 5764, 5765, 656, 657, 5766, 5767, 5768, 5769, 5770, 5771, 4776, 4777, 4778, 4779, 4780, 4781, 4782, 4783, 4784, 4785, 4786, 4787, 4875, 4876, 4877, 4878, 4879, 4880, 2893, 2894, 2895, 2896, 2897, 6626, 6627, 284, 5881, 5882, 5883, 5884, 5885, 5886, 5887, 5888, 5889, 5890, 781, 782, 5891, 5892, 5893, 5894, 5895, 5896, 4901, 4902, 4903, 4904, 4905, 4906, 4907, 4908, 4909, 4910, 4911, 4912, 5000, 5001, 5002, 5003, 5004, 5005, 2526, 2527, 2528, 2529, 3018, 3019, 3020, 3021, 3022, 6751, 6752, 6006, 6007, 6008, 6009, 6010, 6011, 6012, 6013, 6014, 6015, 6016, 6017, 6018, 6019, 6020, 6021, 5026, 5027, 5028, 5029, 5030, 5031, 1876, 1877, 2001, 2000, 1878, 5125, 5126, 5127, 5128, 5129, 5130, 2651, 2652, 2653, 2654, 3143, 2523, 2524, 2525, 3141, 3142, 3146, 3147, 6131, 6132, 6133, 6134, 6135, 6136, 6137, 6138, 6139, 6140, 6141, 6142, 6143, 6144, 6145, 6146, 5154, 5155, 5156, 5250, 5251, 5252, 5253, 5254, 5255, 3268, 2648, 2649, 2650, 3266, 3267, 3271, 3272, 4387, 5278, 5279, 5375, 5376, 5377, 5378, 5379, 5380, 3393, 3395, 3394, 3396, 3397, 5502, 5503, 2521, 2522, 33, 2646, 2647, 34, 158, 159, 660, 661, 4520, 4521, 4522, 4523, 4524, 4525, 5625, 5626, 5627, 5628, 5629, 5630, 785, 786, 4160, 4645, 4646, 4647, 4648, 4649, 4650, 4512, 4513, 4514, 4515, 5750, 5751, 5752, 5753, 5754, 5755, 4030, 4031, 4770, 4771, 4772, 4773, 4774, 4775, 4637, 4638, 4639, 4640, 5875, 5876, 5877, 5878, 5879, 5880, 4155, 4156, 4895, 4896, 4897, 4898, 4899, 4900, 4762, 4763, 4764, 4765, 6000, 6001, 6002, 6003, 6004, 6005, 5522, 5523, 5524, 5525, 5526, 5527, 4280, 4281, 5020, 5021, 5022, 5023, 5024, 5025, 4887, 4888, 4889, 4890, 6127, 6128, 1750, 5647, 5648, 5649, 5650, 5651, 5652, 1380, 2134, 2135, 5145, 5146, 5147, 5148, 5149, 5150, 5012, 5013, 5014, 5015, 5772, 5773, 5774, 5775, 5776, 5777, 1504, 1505, 2259, 2260, 5137, 5138, 5139, 5897, 5898, 5899, 5900, 5901, 5902, 1630, 2384, 2385, 6022, 6023, 6024, 6025, 6026, 6027, 1755, 2381, 2382, 6149, 6150, 6500, 6255, 6256};
	public Collision(Terrain tuilesDeLaMap) {
		this.tuilesDeLaMap = tuilesDeLaMap;
	}

	public boolean peutSeDeplacerIci(int posX, int posY) {

		int indiceColonne;
		int indiceLigne;
		int indiceComposant;

		indiceColonne = posX / 16;
		indiceLigne = posY / 16;
		indiceComposant = indiceColonne + (indiceLigne * this.tuilesDeLaMap.nbTuilesParLigne);
		// indiceComposant correspond � la case du tableau de tuiles o� se situe l'acteur

//		System.out.println("colonne de tuile n�" + indiceColonne + " , ligne de tuile n�" + indiceLigne
//				+ " , index dans le tableau : " + indiceComposant);

		// On voit si indiceComposant est une tuile o� l'acteur peut marcher
		for (int i = 0; i < tuilesInfranchissables.length; i++) {
			if (this.tuilesDeLaMap.getCodeTuilesObstacle()[indiceComposant] == tuilesInfranchissables[i]) {
				return false; 	// L'acteur ne pourra pas marcher ici
			}
		}
		
		return true; // Si indiceComposant ne correspond � aucune tuile de tuilesInfranchissables l'acteur peut marcher
	}
}