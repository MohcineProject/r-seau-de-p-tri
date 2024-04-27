package network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import network.ArcN;
import network.Place;
import network.Transition;

class ArcNTest {

	private static Place myPlace;
	private static Transition myTransition;
	private static ArcN myArcN;

	@BeforeAll
	static void setUp() {
		//  On crée une instance d'arc avant chaque test
		myPlace = new Place(2);
		myTransition = new Transition();
		myArcN = new ArcN(myPlace, myTransition, true, 5);

	}

	@Test
	void testupdateWeight() {
		setUp();
		// On modifie le poids de l'arc
		myArcN.updateWeight(9);

		//// On vérifie que la méthode updateArcValue() a correctement modifié le poids de l'arc
		assertEquals(9, myArcN.getWeight());

	}


	@Test
	void testgetSens() {
		setUp();
		assertEquals(true, myArcN.getSens());	
	}

}
