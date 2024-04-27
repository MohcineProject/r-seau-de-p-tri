package network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import network.Place;
import network.Transition;


import network.Arc;
import network.ArcN;

class ArcTest {

	private static Place myPlace;
	private static Transition myTransition;
	private static Arc myArc1;
	private static Arc myArc2;


	@BeforeAll
	static void setUp() {
		//  On crée une instance d'arc avant chaque test
		myPlace = new Place(2);
		myTransition = new Transition();
		myArc1 = new ArcN(myPlace, myTransition, true, 5);
		myArc2 = new ArcN(myPlace, myTransition, false, -4);


	}



	@Test
	void testgetSens() {
		setUp() ;
		assertEquals(true, myArc1.getSens());	
	}


	@Test
	void testgetPlace() {
		setUp() ;
		assertEquals(myPlace, myArc1.getPlace());

	}


	@Test
	void testgetTransition() {
		setUp() ;
		assertEquals(myTransition, myArc1.getTransition());
	}


	@Test
	void testgetWeight() {
		setUp() ;
		assertEquals(5, myArc1.getWeight());

		// On vérifie que le poids =0 si le paramètre du poids est négatif
		assertEquals(0, myArc2.getWeight());



	}


	@Test
	void testisActivate() {
		setUp() ;
		//On vérifie que l'arc1 n'est pas activate initialement,
		assertFalse(myArc1.IsActivate());

		//On ajoute des jetons à myPlace
		myPlace.setTokens(10);

		//On vérifie que l'arc1 est activate
		assertTrue(myArc1.IsActivate());




	}


	@Test
	void testActivate() {
		setUp() ;

		//On vérifie que l'arc n'est pas activate initialement,
		assertFalse(myArc1.IsActivate());

		//On "active" l'arc et on vérifie qu'il n'a pas eu d'effets.
		myArc1.Activate();
		assertEquals(2, myPlace.getTokens());


		//On ajoute des jetons à myPlace
		myPlace.setTokens(10);

		//On vérifie que l'arc1 est activate
		assertTrue(myArc1.IsActivate());


		//On active l'arc1 et on vérifie son effet (jetons déplacés).
		myArc1.Activate();
		assertEquals(5, myPlace.getTokens());



	}





}
