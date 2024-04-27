package network;

import java.util.ArrayList;

/**
 * The PetriNetwork class represents a Petri network composed of places, arcs, and transitions.
 * It contains methods for adding and removing transitions, places, and arcs, as well as executing transitions, changing places tokens and
 * showing the current state of the Petri network.
 */
public class PetriNetwork implements IPetriNetwork {

	private ArrayList<Arc> arcs;
	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;

	/**
	 * Constructs a new PetriNetwork with empty lists for arcs, places, and transitions.
	 */
	public PetriNetwork() {
		this.arcs = new ArrayList<Arc>();
		this.places = new ArrayList<Place>();
		this.transitions = new ArrayList<Transition>();
	}

	/**
	 * Adds a new transition to the Petri network.
	 */
	public void addTransition() {
		Transition trans = new Transition();
		transitions.add(trans);
	}

	/**
	 * Removes a specified transition from the Petri network along with its associated arcs.
	 *
	 * @param trans The transition to be removed.
	 */
	public void rmTransition(Transition trans) {
		transitions.remove(trans);
		ArrayList<Integer> i = new ArrayList<Integer>();
		for (Arc arc : arcs) {
			if (arc.getTransition() == trans) {
				i.add(arcs.indexOf(arc));
			}
		}
		for (int j : i) {
			arcs.remove(j);
		}

	}

	/**
	 * Adds a new place with a certain number of tokens to the Petri network.
	 *
	 * @param token The number of tokens for the new place.
	 */
	public void addPlace(int token) {
		Place place = new Place(token);
		places.add(place);
	}

	/**
	 * Removes a specified place from the Petri network along with its associated arcs.
	 *
	 * @param place The place to be removed.
	 */
	public void rmPlace(Place place) {
		places.remove(place);
		ArrayList<Integer> i = new ArrayList<Integer>();
		for (Arc arc : arcs) {
			if (arc.getPlace() == place) {
				i.add(arcs.indexOf(arc));
			}
		}
		for (int j : i) {
			arcs.remove(j);
		}

	}

	/**
	 * Adds a new normal arc  to the Petri network.
	 * Also adds the corresponding arc to the associated transition.
	 *
	 * @param place  The place connected to the arc.
	 * @param trans  The transition connected to the arc.
	 * @param sens   The direction of the arc.
	 * @param poids  The weight of the arc.
	 */
	public void addArcN(Place place, Transition trans, boolean sens, int poids) {
		boolean a = false;
		for (Arc arc : arcs) {
			if (arc.getPlace() == place && arc.getTransition() == trans && arc.getSens() == sens) {
				a = true;
			}
		}
		if (!a) {
			ArcN arcn = new ArcN(place, trans, sens, poids);
			this.arcs.add(arcn);
			trans.addArcN(place, trans, sens, poids);
		}
	}

	/**
	 * Adds a new "Arc videur" to the Petri network.
	 * Also adds the corresponding arc to the associated transition.
	 *
	 * @param place The place connected to the arc.
	 * @param trans The transition connected to the arc.
	 */
	public void addArcV(Place place, Transition trans) {
		boolean b = false;
		for (Arc arc : arcs) {
			if (arc.getPlace() == place && arc.getTransition() == trans && arc.getSens()) {
				b = true;
			}
		}
		if (!b) {
			ArcV arcv = new ArcV(place, trans);
			this.arcs.add(arcv);
			trans.addArcV(place, trans);
		}
	}

	/**
	 * Adds a new "Arc zero" to the PetriNetwork.
	 * Also adds the arc to the associated transition.
	 *
	 * @param place The place connected to the arc.
	 * @param trans The transition connected to the arc.
	 */
	public void addArcZ(Place place, Transition trans) {
		boolean c = false;
		for (Arc arc : arcs) {
			if (arc.getPlace() == place && arc.getTransition() == trans && arc.getSens()) {
				c = true;
			}
		}
		if (!c) {
			ArcZ arcz = new ArcZ(place, trans);
			this.arcs.add(arcz);
			trans.addArcZ(place, trans);
		}
	}

	/**
	 * Removes a specified normal arc from the Petri network.
	 *
	 * @param place The place connected to the arc.
	 * @param trans The transition connected to the arc.
	 * @param sens  The direction of the arc.
	 */
	public boolean rmArc(Place place, Transition trans, boolean sens) {
		ArrayList<Integer> i = new ArrayList<Integer>();
		for (Arc arc : arcs) {
			if (arc.getPlace().equals(place) && arc.getTransition().equals(trans) && arc.getSens() == sens && (arc instanceof ArcN)) {
				i.add(arcs.indexOf(arc));
			}
		}
		for (int j : i) {
			arcs.remove(j);
		}
		if (i.size( )==0) {
			return false ;
		} else {
			return true ;
		}
	}

	/**
	 * Removes a specified arc (ArcV or ArcZ) from the Petri network.
	 *
	 * @param place The place connected to the arc.
	 * @param trans The transition connected to the arc.
	 */
	public boolean rmArc(Place place, Transition trans) {
		ArrayList<Integer> i = new ArrayList<Integer>();
		for (Arc arc : arcs) {
			if (arc.getPlace().equals(place) && arc.getTransition().equals(trans) && (arc instanceof ArcV || arc instanceof ArcZ)) {
				i.add(arcs.indexOf(arc));

			}

		}
		for (int j : i) {
			arcs.remove(j);
		}


		if (i.size( )==0) {
			return false ;
		} else {
			return true ;
		}
	}

	/**
	 * Changes the number of tokens for a specified place in the Petri network.
	 *
	 * @param place  The place to be updated.
	 * @param jetons The new number of tokens for the place.
	 */
	public void setTokenPlace(Place place, int jetons) {
		place.setTokens(jetons);
	}

	/**
	 * Returns the list of places in the Petri network.
	 *
	 * @return The list of places.
	 */
	public ArrayList<Place> getPlaces() {
		return places;
	}

	/**
	 * Returns the list of arcs in the Petri network.
	 *
	 * @return The list of arcs.
	 */
	public ArrayList<Arc> getArcs() {
		return arcs;
	}

	/**
	 * Returns the list of transitions in the Petri network.
	 *
	 * @return The list of transitions.
	 */
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	/**
	 * Fires a specified transition in the Petri network.
	 *
	 * @param trans The transition to be fired.
	 */
	public void Execute(Transition trans){
		trans.fire();
	}

	/**
	 * Displays the Petri network including places, transitions, and arcs.
	 *
	 * @return A string containing the Petri network summary.
	 */
	public String showPetri() {
		// Summary of places
		String sumPLaces = "Places :" + "\n";
		for (int i = 0; i < this.getPlaces().size(); i++) {
			sumPLaces += "P" + String.valueOf(i) + " => jetons : " + String.valueOf(this.getPlaces().get(i).getTokens()) + "\n";
		}

		// Summary of transitions
		String sumTransitions = "Transitions :" + "\n";
		for (int i = 0; i < this.getTransitions().size(); i++) {
			sumTransitions += "T" + String.valueOf(i) + "\n";
		}

		// Summary of Arcs
		String sumArcs = "Arcs :" + "\n";
		String arcsens;
		String Name;
		String poids;
		for (int i = 0; i < this.getArcs().size(); i++) {
			if (this.getArcs().get(i).getSens()) {
				arcsens = " -> ";
			} else {
				arcsens = " <- ";
			}
			Place pl = this.getArcs().get(i).getPlace();
			Transition tr = this.getArcs().get(i).getTransition();
			Name = this.getArcs().get(i).getClass().getName();
			if (Name.equals("network.ArcN")) {
				Name += " weight : ";
				poids = String.valueOf(this.getArcs().get(i).getWeight());
			} else {
				poids = "";
			}
			sumArcs += "Arc " + String.valueOf(i) + ": " + "P" + this.getPlaces().indexOf(pl) + arcsens + "T" + this.getTransitions().indexOf(tr) + " type : " + Name + poids + "\n";

		}

		return ("Petri summary :" + "\n"
				+ sumPLaces
				+ sumTransitions
				+ sumArcs);
	}
}
