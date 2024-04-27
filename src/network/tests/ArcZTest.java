package network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import network.ArcN;
import network.ArcZ;
import network.Place;
import network.Transition;

class ArcZTest {

	private static Place myPlace;
	private static Transition myTransition;
	private static ArcZ myArcZ;

	@BeforeAll
	static void setUp() {
		//  On crée une instance d'arc avant chaque test
		myPlace = new Place(5);
		myTransition = new Transition();
		myArcZ = new ArcZ(myPlace, myTransition);

	}

	@Test
	void testisActivate() {
		setUp() ;
		//On vérifie que l'arc n'est pas activate initialement,
		assertFalse(myArcZ.IsActivate());

		//On vide myPlace
		myPlace.setTokens(0);

		//On vérifie que l'arc est activate
		assertTrue(myArcZ.IsActivate());


	}


	@Test
	void testActivate() {
		setUp() ;
		//There is no code for activate for arcZ 
		myArcZ.Activate();

	}


}
