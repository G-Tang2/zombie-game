package game;

import java.util.Random;

public class Farmer extends Human {

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the farmer in the UI
	 * @param displayChar Character to represent the farmer in the UI
	 * @param hitPoints   Farmers's starting number of hitpoints
	 */
	protected Farmer(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

}
