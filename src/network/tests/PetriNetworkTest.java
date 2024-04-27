package network.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import network.ArcN;
import network.ArcV;
import network.ArcZ;
import network.PetriNetwork;
import network.Place;
import network.Transition;

class PetriNetworkTest {
	static PetriNetwork pn  ;

	@BeforeAll
	static void  setUp() {
		//set up a new PetriNetwork with no initial configuration  
		pn = new PetriNetwork() ; 

	}

	@Test 
	void testAddPlace() {
		setUp();
		//we check if the function complete the list correctly
		for (int i = 0 ; i < 10 ; i++ ) {
			pn.addPlace(i);
		}
		//we assert if the places were created 
		assertEquals(pn.getPlaces().size(), 10);
		//we assert if they were created with the right number of tokens 
		for (int i = 0 ; i < 10 ; i++ ) {
			assertEquals(pn.getPlaces().get(i).getTokens(), i ) ;
		}
	}
	@Test
	void testAddTransition() {
		setUp();
		//we check if the function complete the list correctly
		for (int i = 0 ; i < 10 ; i++ ) {
			pn.addTransition();
		}
		//we assert if the transitions were created 
		assertEquals(pn.getTransitions().size(), 10);
	}

	@Test
	void testRmTransition() {
		setUp();
		//we add 3 transitions  
		for (int i = 0 ; i < 3 ; i++ ) {
			pn.addTransition();
		}
		//we create the transition to remove
		Transition trans = pn.getTransitions().get(0);
		//we check if the removal did occur y viewing the size and the existence of the transition
		pn.rmTransition(trans);
		assertNotEquals(trans, pn.getTransitions().get(0) );
		assertEquals(pn.getTransitions().size(), 2);
		//we need to check the removal of arcs related to the removed transition 
		//first we create an arc and we use it to attach it to a place and a transition

		pn.addPlace(0);
		trans = pn.getTransitions().get(0);
		pn.addArcN(pn.getPlaces().get(0), trans, true, 7);
		//we check the size of the arc list before and after the removal
		assertEquals(1, pn.getArcs().size());
		pn.rmTransition(trans); 
		assertEquals(0, pn.getArcs().size()) ;


	}
	@Test
	void testRmPlace () {
		setUp();
		//we add 3 places  
		for (int i = 0 ; i < 3 ; i++ ) {
			pn.addPlace(i);
		}
		//we remove the first place 
		Place place = pn.getPlaces().get(0);
		pn.rmPlace(place);
		//we verify if the place was removed
		assertNotEquals(place, pn.getPlaces().get(0) );
		assertEquals(pn.getPlaces().size(), 2);
		//we need to check the removal of arcs related to the removed place 
		//first we create an arc and we use it to attach it to a place and a transition

		pn.addTransition();
		place = pn.getPlaces().get(0);
		pn.addArcN(place, pn.getTransitions().get(0), true, 7);
		//we check the size of the arc list before and after the removal
		assertEquals(1, pn.getArcs().size());
		pn.rmPlace(place); 
		assertEquals(0, pn.getArcs().size());



	}

	@Test 
	void testAddArcN() {
		setUp();
		//We create 8 normal arc N attached to 8 places and 8 transitions
		boolean sens ; 
		for (int i = 0 ; i < 8 ; i++ ) {
			pn.addPlace(i);
			pn.addTransition();
			if (i%2 == 0) sens = true ; 
			else  sens = false ;
			pn.addArcN(pn.getPlaces().get(i), pn.getTransitions().get(i), sens, i);
		}

		//We add an "arc doublée" ==> arc 7 doublé
		pn.addArcN(pn.getPlaces().get(7), pn.getTransitions().get(7), false, 5);

		//We add an "arc doublée" ==> arc 6 doublé, the second is arcvideur
		pn.addArcV(pn.getPlaces().get(6), pn.getTransitions().get(6));

		//We add an "arc doublée" ==> arc 5 doublé, the second is arczero
		pn.addArcZ(pn.getPlaces().get(4), pn.getTransitions().get(4));

		// we verify that "arc doublé" was not added  
		//==> only one arc between the 7th place and 8th transition is conserved (tokens =7 not 5)

		//(1)we verify the size=8 not 11
		assertEquals(pn.getArcs().size(), 8);

		//(2)we verify the conformity of the attributes of the normal arc
		for (int i = 0 ; i < 8 ; i++ ) { //arcV et arcZ were not added
			ArcN arcn = (ArcN) pn.getArcs().get(0) ; 
			assertEquals(arcn.getPlace(), pn.getPlaces().get(0));
			assertEquals(arcn.getTransition(), pn.getTransitions().get(0));
			assertEquals(arcn.getSens(), true);
			assertEquals(arcn.getWeight(), 0);
		}

	}


	@Test 
	void testAddArcV() {
		setUp();
		//We create 8 normal arc Videurs attached to 8 places and 8 transitions

		for (int i = 0 ; i < 8 ; i++ ) {
			pn.addPlace(i);
			pn.addTransition();
			pn.addArcV(pn.getPlaces().get(i), pn.getTransitions().get(i));
		}

		//We add an "arc doublée" ==> arc 6 doublé, the second is arcvideur
		pn.addArcV(pn.getPlaces().get(6), pn.getTransitions().get(6));

		// we verify that tha "arc doublé" was not added  (arcvideur not added)
		//==> only one arc between the 7th place and 8th transition is conserved (tokens =7 not 5)

		//(1)we verify the size =8 not 9
		assertEquals(pn.getArcs().size(), 8);
		//(2)we verify the conformity of the attributes of the normal arc
		ArcV arcn = (ArcV) pn.getArcs().get(0) ; 
		assertEquals(arcn.getPlace(), pn.getPlaces().get(0));
		assertEquals(arcn.getTransition(), pn.getTransitions().get(0));



	}

	@Test 
	void testAddArcZ() {
		setUp();
		//We create 8 normal arc Videurs attached to 8 places and 8 transitions

		for (int i = 0 ; i < 8 ; i++ ) {
			pn.addPlace(i);
			pn.addTransition();
			pn.addArcZ(pn.getPlaces().get(i), pn.getTransitions().get(i));

		}
		//we verify the size
		assertEquals(pn.getArcs().size(), 8);
		//we verify the conformity of the attributes of the normal arc
		ArcZ arcn = (ArcZ) pn.getArcs().get(0) ; 
		assertEquals(arcn.getPlace(), pn.getPlaces().get(0));
		assertEquals(arcn.getTransition(), pn.getTransitions().get(0));



	}


	@Test 
	void testRmArc1() {
		setUp();
		//we initialize an ArcN 
		Transition trans = new Transition() ; 
		Place place = new Place(1) ; 
		pn.addArcN(place,trans, true, 4);
		//We assert the existence of the arc
		assertEquals(1, pn.getArcs().size());
		//we assert the removal of the arc
		pn.rmArc(place, trans, true);
		assertEquals(0, pn.getArcs().size());

	}




	@Test 
	void testRmArc2() {
		setUp();
		//we initialize an ArcV and an ArcZ
		Transition trans = new Transition() ; 
		Place place = new Place(1) ; 
		pn.addArcV(place,trans);
		Transition trans1 = new Transition() ; 
		Place place1 = new Place(3) ; 
		pn.addArcZ(place1,trans1);
		//We assert the existence of the arcs
		assertEquals(2, pn.getArcs().size());
		//we assert the removal of the arc
		pn.rmArc(place, trans);
		assertEquals(1, pn.getArcs().size());

	}

	@Test 
	void testSetJetons() {
		setUp();
		//we initialize a place 0 tokens 
		Place place = new Place(0) ; 
		//we set the tokens 
		pn.setTokenPlace(place, 8);
		//we verify the modification 
		assertEquals(8, place.getTokens());
	}
	@Test
	void testShowPetri() {
		setUp();
		String result ; 
		//First Case : Isolated Transition 
		pn.addTransition();
		result = "Petri summary :\n"
				+ "Places :\n"
				+ "Transitions :\n"
				+ "T0\n"
				+ "Arcs :\n"
				+ ""
				; 
		assertEquals(result ,pn.showPetri());
		//Second Case : Place (0 tokens) - normal Arc(poids :0) - transition - Normal Arc (poids :0)  - Place(0 tokens) ; 
		setUp();
		pn.addPlace(0);
		pn.addTransition();
		pn.addPlace(0);
		pn.addArcN(pn.getPlaces().get(0), pn.getTransitions().get(0), true, 0);
		pn.addArcN(pn.getPlaces().get(1), pn.getTransitions().get(0), false, 0);
		result = "Petri summary :\n"
				+ "Places :\n"
				+ "P0 => jetons : 0\n"
				+ "P1 => jetons : 0\n"
				+ "Transitions :\n"
				+ "T0\n"
				+ "Arcs :\n"
				+ "Arc 0: P0 -> T0 type : network.ArcN weight : 0\n"
				+ "Arc 1: P1 <- T0 type : network.ArcN weight : 0\n"
				+ "" ;
		assertEquals(result ,pn.showPetri());
		//Third Case : Place (-4 tokens) - normal Arc(poids :3) - transition - Normal Arc (poids :3)  - Place(4 tokens) ; 
		setUp();
		pn.addPlace(-4); // Tokens will be set to 0 
		pn.addTransition();
		pn.addPlace(4);
		pn.addArcN(pn.getPlaces().get(0), pn.getTransitions().get(0), true, 3);
		pn.addArcN(pn.getPlaces().get(1), pn.getTransitions().get(0), false, 3);
		result = "Petri summary :\n"
				+ "Places :\n"
				+ "P0 => jetons : 0\n"
				+ "P1 => jetons : 4\n"
				+ "Transitions :\n"
				+ "T0\n"
				+ "Arcs :\n"
				+ "Arc 0: P0 -> T0 type : network.ArcN weight : 3\n"
				+ "Arc 1: P1 <- T0 type : network.ArcN weight : 3\n"
				+ "" ;
		assertEquals(result ,pn.showPetri());
		//Fourth Case : Place (4 tokens) - normal Arc(poids : 1) * 2 - Transition
		setUp();
		pn.addPlace(4);
		pn.addTransition();
		pn.addArcN(pn.getPlaces().get(0), pn.getTransitions().get(0), true, 1);
		pn.addArcN(pn.getPlaces().get(0), pn.getTransitions().get(0), true, 1); //This arc should not be added
		result = "Petri summary :\n"
				+ "Places :\n"
				+ "P0 => jetons : 4\n"
				+ "Transitions :\n"
				+ "T0\n"
				+ "Arcs :\n"
				+ "Arc 0: P0 -> T0 type : network.ArcN weight : 1\n"
				+ "" ;
		assertEquals(result ,pn.showPetri());


	}
	@Test 
	void testExecute() {
		setUp();
		//Case 1 : Place - ArcN - Transition -ArcN -Place
		pn.addPlace(10);
		pn.addTransition();
		pn.addArcN(pn.getPlaces().get(0), pn.getTransitions().get(0), true, 2);
		pn.addPlace(10);
		pn.addArcN(pn.getPlaces().get(1), pn.getTransitions().get(0), false, 3);
		pn.Execute(pn.getTransitions().get(0));
		assertEquals(8, pn.getPlaces().get(0).getTokens());
		assertEquals(13, pn.getPlaces().get(1).getTokens());
		//Case 2 : add Place - ArcV -Transition and Place -arcZ-Transition (we will study
		// the case when the transition can't fire in the transitionTest. 
		pn.addPlace(0);
		pn.addArcZ(pn.getPlaces().get(2), pn.getTransitions().get(0));
		pn.addPlace(100);
		pn.addArcV(pn.getPlaces().get(3), pn.getTransitions().get(0));
		pn.Execute(pn.getTransitions().get(0));
		assertEquals(6, pn.getPlaces().get(0).getTokens());
		assertEquals(16, pn.getPlaces().get(1).getTokens());
		assertEquals(0, pn.getPlaces().get(2).getTokens());
		assertEquals(0, pn.getPlaces().get(3).getTokens());





	}


}
