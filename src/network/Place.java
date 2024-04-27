package network;

/**
 * The  Place class represents a place in a network with a specific number of tokens.
 * It allows the addition or subtraction of tokens from the place.
 */
public class Place {

	/**
	 * The number of tokens in the place.
	 */
	private int nbrTokens;

	/**
	 * Constructs a new Place with the specified number of tokens.
	 * If the number in parameter is negative, the number of tokens is set to 0.
	 *
	 * @param n the initial number of tokens in the place.
	 */
	public Place(int n) {
		if (n < 0) {
			this.nbrTokens = 0;
		} else {
			this.nbrTokens = n;
		}
	}

	/**
	 * Returns the number of tokens in the place.
	 *
	 * @return the number of tokens in the place.
	 */
	public int getTokens() {
		return this.nbrTokens;
	}

	/**
	 * Sets the number of tokens in the place. It allows the addition (new number of tokens > current number of tokens) or subtraction (new number of tokens < current number of tokens) of tokens from the place.
	 * If the new number (in parameter) is negative, the number of tokens is set to 0.
	 *
	 * @param n the new number of tokens in the place.
	 */
	public void setTokens(int n) { // Allows to add and remove tokens
		if (n < 0) {
			this.nbrTokens = 0;
		} else {
			this.nbrTokens = n;
		}
	}
}
