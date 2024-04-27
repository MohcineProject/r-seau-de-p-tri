package network;

/**
 * The arc class is the abstraction of the arc entity of a PetriNetwork. It contains information about the place and the transition 
 * it links, as well as the weight and the direction of the arc.
 * The class manages as well the activations of the arcs. An arc can be activated if all his conditions are met. The activation is used by the 
 * fire method of transition, it modifies the content of the places linked to the transition (with some exceptions).
 */

public abstract class Arc {
	/**
	 * The weight of the arc, determines the number of tokens used by the arc when the transition is fired.
	 */
	protected int weight;
	/**
	 * The direction of the arc, either from Place to Transition (true) or Transition to Place (false).
	 */
	protected boolean sens;
	/**
	 * The place connected to the arc.
	 */
	protected Place place;
	/**
	 * The transition connected to the arc.
	 */
	protected Transition transition;




	/**
	 * Constructs an Arc with the specified Place, Transition, direction, and weight.
	 *
	 * @param place      The Place connected to the arc.
	 * @param transition The Transition connected to the arc.
	 * @param sens       The direction of the arc (true for Place to Transition, false for Transition to Place).
	 * @param weight     The weight of the arc, if the input is negative it is set to zero.
	 */

	public Arc(Place place, Transition transition, boolean sens, int weight) {  //Constructor of Arc class
		this.sens=sens;
		this.place=place;
		this.transition=transition;
		if (weight<0) {   // The weight should always be positif
			this.weight=0;
		}else {
			this.weight=weight;
		}


	}
	/**
	 * Return the direction of the arc.
	 *
	 * @return true if the arc is from Place to Transition, false if otherwise.
	 */

	public boolean getSens() {  
		return this.sens ; 
	}
	/**
	 * Returns the Place connected to the arc.
	 *
	 * @return The Place connected to the arc.
	 */
	public Place getPlace(){
		return this.place;	
	}


	/**
	 * Returns the Transition connected to the arc.
	 *
	 * @return The Transition connected to the arc.
	 */
	public Transition getTransition(){
		return this.transition;	
	}


	/**
	 * Returns the weight connected to the arc.
	 *
	 * @return The weight connected to the arc.
	 */
	public int getWeight() {
		return this.weight ; 
	}

	/**
	 * Returns if the arc can be activated or not. 
	 *
	 * @return true if the arc can be activated.  
	 */
	public abstract boolean IsActivate();

	/**
	 * Activates the arc.
	 */

	public abstract void Activate();


}
