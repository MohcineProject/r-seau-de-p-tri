package network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import network.Place;
import network.Transition;

class TransitionTest {

	private static Place myPlace1;
	private static Place myPlace2;
	private static Place myPlace3;
	private static Place myPlace4;
	private static Transition myTransition;


	@BeforeAll
	static void setUp() {
		myPlace1 = new Place(2);
		myPlace2 = new Place(0);
		myPlace3 = new Place(9);
		myPlace4 = new Place(3);
		myTransition = new Transition();
	}


	@Test
	void testisFireable() {
		setUp();
		// On ajoute un arc normal à la transition de façon qu'elle ne soit pas tirable, 
		//et on vérifie qu'elle n'est pas tirable.
		myTransition.addArcN(myPlace1, myTransition, true, 6);//2<6 not activate
		myTransition.addArcN(myPlace4, myTransition, false, 3); // entrant => activate
		assertFalse(myTransition.isFireable());

		//On rend la transition tirable et on vérifie qu'elle est tirable.
		myPlace1.setTokens(8); // 8>6
		assertTrue(myTransition.isFireable());

		// On ajoute un arc videur à la transition de façon qu'elle ne soit pas tirable, 
		//et on vérifie qu'elle n'est pas tirable.
		myTransition.addArcV(myPlace2, myTransition); // tokens<1 ==> not activate
		assertFalse(myTransition.isFireable());

		//On rend la transition tirable et on vérifie qu'elle est tirable.
		myPlace2.setTokens(3); // 3>1 ==> arc activate
		assertTrue(myTransition.isFireable());

		// On ajoute un arc zéro à la transition de façon qu'elle ne soit pas tirable, 
		//et on vérifie qu'elle n'est pas tirable.
		myTransition.addArcZ(myPlace3, myTransition); // tokens!=0 ==> not activate
		assertFalse(myTransition.isFireable());

		//On rend la transition tirable et on vérifie qu'elle est tirable.
		myPlace3.setTokens(0); 
		assertTrue(myTransition.isFireable());

	}

	@Test
	void testfire() {
		setUp();
		//On ajoute un arc à la transition de façon qu'elle ne soit pas tirable, 
		//On vérifie qu'elle n'est pas tirable,
		myTransition.addArcN(myPlace1, myTransition, true, 5); //5>2 ==> not activate
		myTransition.addArcN(myPlace4, myTransition, false, 3); // entrant => activate
		myTransition.addArcV(myPlace2, myTransition); // tokens<1 ==> not activate
		myTransition.addArcZ(myPlace3, myTransition); // tokens!=0 ==> not activate
		assertFalse(myTransition.isFireable());

		//On déclenche la transition et on vérifie qu'elle n'a pas eu d'effets.
		myTransition.fire();
		assertEquals(2, myPlace1.getTokens());
		assertEquals(0, myPlace2.getTokens());
		assertEquals(9, myPlace3.getTokens());


		//On ajoute des jetons à myPlace1 => premier arc activate,
		//On ajoute des jetons à myPlace2==> deuxième arc activate,
		//On vide myPlace3 ==> troixième arc activate
		//On vérifie que la transition est encore tirable,
		myPlace1.setTokens(5);
		myPlace2.setTokens(3);
		myPlace3.setTokens(0);
		assertTrue(myTransition.isFireable());

		//On déclenche la transition et on vérifie ses effets (arc activés et jetons déplacés).
		myTransition.fire();
		assertEquals(0, myPlace1.getTokens());
		assertEquals(0, myPlace2.getTokens());
		assertEquals(0, myPlace3.getTokens());
		assertEquals(6, myPlace4.getTokens());



	}



}
