package projetJAY.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CollisionTest {

	@Test
	public final void testPeutSeDeplacerIci() {
		ZoneDeJeu jeu = new ZoneDeJeu();
		Joueur Link = new Joueur(990,1000,jeu);
		assertTrue(Link.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(500, 500), " cas déplacement spawn du joueur");
		assertTrue(Link.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(630, 570), "cas non spawn et non obstacle");
		assertFalse(Link.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(1240, 885), " cas déplacement obstacle arbre");
		assertFalse(Link.getJeu().getCollisionsDesActeurs().peutSeDeplacerIci(1305, 1580), " cas déplacement obstacle mur");
}
}
