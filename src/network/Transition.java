package network;

import java.util.ArrayList;

/**
 * The Transition class represents a transition in a network with a list of arcs.
 * Transitions can be fired, and their firing depends on the activation status of their arcs.
 */
public class Transition {

	/**
	 * The list of arcs associated with the transition.
	 */
	private ArrayList<Arc> arcs;

	/**
	 * Constructor of the Transition class.
	 */
	public Transition() {
		arcs = new ArrayList<Arc>();
	}

	/**
	 * Verifies if the transition is fireable.
	 *
	 * @return  true if the transition is fireable,  false if not.
	 */
	public boolean isFireable() {
		for (Arc arc : arcs) {
			// cond: All transitions's arcs should be activate
			if (!arc.IsActivate()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Activates all transition's arcs if the transition is fireable.
	 * Prints a message to the console if the transition is not fireable.
	 */
	public void fire() {
		if (this.isFireable()) {
			for (Arc arc : arcs) {
				arc.Activate();
			}
		}
	}

	/**
	 * Adds an "Arc normal" to the transition's arcs.
	 *
	 * @param place the place connected to the arc.
	 * @param trans the transition connected to the arc.
	 * @param sens  the direction of the arc (true for input, false for output).
	 * @param poids the weight of the arc.
	 */
	public void addArcN(Place place, Transition trans, boolean sens, int poids) {
		ArcN arcn = new ArcN(place, trans, sens, poids);
		this.arcs.add(arcn);
	}

	/**
	 * Adds an "Arv videur" to the transition's arcs.
	 *
	 * @param place the place connected to the arc.
	 * @param trans the transition connected to the arc.
	 */
	public void addArcV(Place place, Transition trans) {
		ArcV arcv = new ArcV(place, trans);
		this.arcs.add(arcv);
	}

	/**
	 * Adds an "Arv zéro" to the transition's arcs.
	 *
	 * @param place the place connected to the arc.
	 * @param trans the transition connected to the arc.
	 */
	public void addArcZ(Place place, Transition trans) {
		ArcZ arcz = new ArcZ(place, trans);
		this.arcs.add(arcz);
	}
}
