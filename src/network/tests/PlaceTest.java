package network.tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import network.Place;

class PlaceTest {

	private static Place myPlace;
	private static Place myPlace1;


	@BeforeAll
	static void setUp() {
		//  On crée une instance de Place avec 7 jetons avant chaque test
		myPlace = new Place(7);
		myPlace1 = new Place(-5);
	}


	@Test
	void testconstructeur() {
		assertEquals(0, myPlace1.getTokens());
	}

	@Test
	void testGetJetons() {
		setUp();
		// On vérifie que la méthode getJetons() renvoie le nombre de jetons attendu
		assertEquals(7, myPlace.getTokens());
	}


	@Test
	void testSetJetons() {
		setUp();
		// On modifie le nombre de jetons dans la place
		myPlace.setTokens(20); 

		// On vérifie que la méthode setJetons() a correctement modifié le nombre de jetons
		assertEquals(20, myPlace.getTokens());

		// On modifie le nombre de jetons dans la place (nbre négatif)
		myPlace.setTokens(-5); 

		// On vérifie que la méthode setJetons() a correctement modifié le nombre de jetons
		assertEquals(0, myPlace.getTokens());
	}

}
