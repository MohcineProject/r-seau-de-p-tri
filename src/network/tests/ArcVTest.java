package network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import network.ArcV;
import network.Place;
import network.Transition;

class ArcVTest {

	private static Place myPlace;
	private static Transition myTransition;
	private static ArcV myArcV;

	@BeforeAll
	static void setUp() {
		//  On crée une instance d'arc avant chaque test
		myPlace = new Place(0);
		myTransition = new Transition();
		myArcV = new ArcV(myPlace, myTransition);

	}

	@Test
	void testisActivate() {
		setUp() ;
		//On vérifie que l'arc n'est pas activate initialement,
		assertFalse(myArcV.IsActivate());

		//On ajoute des jetons à myPlace
		myPlace.setTokens(3);

		//On vérifie que l'arc est activate
		assertTrue(myArcV.IsActivate());


	}


	@Test
	void testActivate() {
		setUp() ;
		//On vérifie que l'arc n'est pas activate initialement,
		assertFalse(myArcV.IsActivate());


		//On ajoute des jetons à myPlace
		myPlace.setTokens(10);

		//On vérifie que l'arc est activate
		assertTrue(myArcV.IsActivate());


		//On active l'arc et on vérifie son effet (Place vidée).
		myArcV.Activate();
		assertEquals(0, myPlace.getTokens());

	}



}
