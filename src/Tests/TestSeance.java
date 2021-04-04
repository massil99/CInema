package Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Salles.Salle;
import Seances.Seance;
import Seances.Tarif;



//@BeforeAll   @BeforeEach

class TestSeance {
	Seance seance;

	@Test
	//leTestDuCasValide
	void simpleTestCase() {
		Salle salle = new Salle(5);
		Seance seance = new Seance(0, null, null, salle, null, null, 0);
		assertTrue(seance.reserver(Tarif.Matin));
	}
	@Test
	//LeTestDuCasInvalide
	void fullCapacity() {
		Salle salle = new Salle(0);
		Seance seance = new Seance(0, null, null, salle, null, null, 0);
		assertFalse(seance.reserver(Tarif.Matin));
	}
	@Test
	void testTarif() {
		
		Salle salle = new Salle(5);
		Seance seance = new Seance(0, null, null, salle, null, null, 0);
		assertTrue(seance.reserver(Tarif.Reduit));
	}
    
}
