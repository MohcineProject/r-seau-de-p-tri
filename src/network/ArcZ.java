package network;
/**
 * The acrZ is the abstraction of the "zero arc", it needs to be activated a place with zero tokens.
 * It inherits features such as associated Place and Transition, we fix the direction to true (since it is always the case) and we set the weight to zero since 
 * it will not be used.
 */
public class ArcZ extends Arc{

	/**
	 * Constructs an Arc Zero with the specified Place and Transition.
	 * The arc is from the place to transition. The weight is set to zero as it will not be used. 
	 *
	 * @param place      The Place connected to the arc.
	 * @param transition The Transition connected to the arc.
	 */
	//Constructor of "ArcZero"
	public ArcZ(Place place, Transition trans) {
		super( place, trans, true,0);
	}
	/**
	 * Check if the arc can be activated (the tokens in place must be 0 ) 
	 *
	 * @return true if the Place is empty, false otherwise.
	 */
	@Override
	public boolean IsActivate() {  //Verify if an arc is activate (arc zero) 
		if(this.getPlace().getTokens()==0 ) { //cond: The place source should be empty 
			return true;
		}else {
			return false;
		}
	}

	/**
	 * ArcZ type do not have any activation action, we override the method since it is abstract.
	 */

	@Override
	public void Activate() { // No changes on place tokens
	}		

}
