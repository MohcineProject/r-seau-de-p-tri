package network;
/**
 * The ArcN reify a normal arc in PetriNetwork. It uses the weight value and the direction to modify content of the associated place 
 * accordingly.
 */
public class ArcN extends Arc{
	/**
	 * Constructs an normal Arc with the specified Place, Transition, direction, and weight, inheriting from Arc.
	 *
	 * @param place      The Place connected to the arc.
	 * @param transition The Transition connected to the arc.
	 * @param sens       The direction of the arc (true for Place to Transition, false for Transition to Place).
	 * @param weight     The weight of the arc. If the input is negative it would be set to zero (in Arc). 
	 */
	//Constructor of "arc normal"
	public ArcN(Place place, Transition transition, boolean sens, int weight) {
		super(place, transition, sens, weight);

	}


	/**
	 * Updates the weight of the arc with a new value.
	 *
	 * @param n The new weight.
	 */
	public void updateWeight(int n) { // updates arc weight
		this.weight=n;
	}

	/**
	 * Returns if the arc can be activated or not
	 *
	 * @return true if the arc can be activated ( number of tokens of the place is superior than the weight or the arc is towards 
	 * transition) . 
	 */
	@Override
	public boolean IsActivate() {  //Verify if an arc is activate (normal arcs) 
		// if the arc is from place to transition ==> cond : place tokens> arc weight
		if(this.sens == true) {
			if(this.getPlace().getTokens()>= this.weight) {
				return true;
			}else {return false;  // an arc from transition to place is always activate
			}
		}else { return true;
		}	
	}

	/**
	 * Activates the arc by updating the tokens in the connected Place based on the arc's direction and weight.
	 */
	@Override
	public void Activate() {     // updates the tokens of the places of the arc
		if (this.IsActivate()) {
			if (this.sens==true) { // case1: arc from place to transition
				int tokens=this.getPlace().getTokens()- this.weight;
				this.place.setTokens(tokens);	
			}else { //  case2: arc from transition to place
				int tokens=this.getPlace().getTokens()+ this.weight;
				this.place.setTokens(tokens);
			}	
		}
	}




}
