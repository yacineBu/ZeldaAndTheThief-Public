
package projetJAY.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArmeTest {

	@Test
	public final void CaractéristiquesArmestest() {
		ZoneDeJeu jeu = new ZoneDeJeu();
		Arme baton = new Baton(3000, jeu);
		assertEquals(20, baton.getPxDePortee(),"cas portée = 20");
		assertEquals(10, baton.getPtAttaque(),"cas attaque = 10");
		assertEquals(5000, baton.getTempsCooldown(),"cas cooldown = 5s");
		Arme arc = new Arc(3000,jeu);
		assertEquals(20, arc.getPxDePortee(),"cas portée = 20");
		assertEquals(10, arc.getPtAttaque(),"cas attaque = 10");
		assertEquals(5000, arc.getTempsCooldown(),"cas cooldown = 5s");
	}

}