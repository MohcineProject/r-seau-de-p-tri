package network;



public interface IPetriNetwork {

	/**
	 * Adds a new transition to the PetriNetwork.
	 */	
	public void addTransition() ;
	/**
	 * remove a transition from the PetriNetwork.
	 * @param the transition to be removed
	 */
	public void rmTransition(Transition trans) ;

	/**
	 * Adds a new Place to the PetriNetwork.
	 * @param number of tokens (must be positive if it is negative it is set to zero instead)
	 */
	public void addPlace(int poids) ;

	/**
	 * Remove a Place from the PetriNet.
	 * @param The place to be removed
	 */
	public void rmPlace(Place place) ;

	/**
	 * Adds a new normal Arc to the PetriNetwork.
	 * @param the place linked to the arc 
	 * @param the transition linked to the arc
	 * @param the direction of the arc (true if it is from place to transition, false if otherwise) 
	 * @param the weight of the normal arc
	 */
	public void addArcN(Place place, Transition trans , boolean sens , int poids) ;
	/**
	 * Adds a new Arc Videur to the PetriNetwork.
	 * @param the place linked to the arc 
	 * @param the transition linked to the arc
	 */
	public void addArcV(Place place , Transition trans);
	/**
	 * Adds a new Arc Zero to the PetriNetwork.
	 * @param the place linked to the arc 
	 * @param the transition linked to the arc
	 */
	public void addArcZ(Place place , Transition trans ) ;
	/**
	 * Remove the given arc form the PetriNetwork. This first signature is used for normal arcs
	 * @param specifies the place of the arc 
	 * @param specifies the transition of the arc 
	 * @param specifies the direction of the arc 
	 */
	public boolean rmArc(Place place , Transition trans , boolean sens ) ;
	/**
	 * Remove the given arc form the PetriNetwork. This first signature is used for arc videur and arc zero
	 * @param specifies the place of the arc 
	 * @param specifies the transition of the arc 
	 */
	public boolean rmArc(Place place , Transition trans) ;
	/**
	 * Fire a selected transition
	 * @param The transition to be fired 
	 */
	public void Execute(Transition trans) ;
}
