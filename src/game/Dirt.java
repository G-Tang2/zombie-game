package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
	}

	@Override
	public Actions allowableActions(Location location) {
		return new Actions();
	}
}
