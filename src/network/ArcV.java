package network;
/**
 * The ArcV class represents an "arc videur" in a PetriNet, extending the Arc class.
 * It inherits features such as associated Place and Transition, we fix the direction to true (since it is always the case) and we set the weight to zero since 
 * it will not be used.
 */

public class ArcV extends Arc{

	/**
	 * Constructs an ArcV representing an "Arc videur" with the specified Place and Transition.
	 * This type of arc only requires a Place and a Transition. The direction is always from Place to Transition, and the weight is 
	 * set the zero (those too values will not be used).
	 *
	 * @param place      The Place connected to the arc.
	 * @param transition The Transition connected to the arc.
	 */
	//Constructor of "arc videur"
	public ArcV(Place place, Transition transition) {
		super( place, transition, true,0);

	}
	/**
	 * Checks if the place contains at least one token so that the arc can be activated
	 *
	 * @return true if the Place is not empty, false otherwise.
	 */
	@Override
	public boolean IsActivate() {  // //Verify if an arc is activate (arcs videurs) 
		if(this.getPlace().getTokens()>= 1) { //cond: place should not be empty
			return true;
		}else {
			return false;
		}
	}


	/**
	 * Activates the arc if it is possible. Set the tokens number in the place to zero
	 */
	@Override
	public void Activate() {  // updates place tokens ==> empty the place
		if (this.IsActivate()) {
			this.place.setTokens(0);
		}
	}		

}
