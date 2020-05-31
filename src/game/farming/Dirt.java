package game.farming;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	public Dirt() {
		super('.');
		addCapability(GroundCapability.SOWABLE);
	}

	/**
	 * getter needed for foods which is not needed for this class but is required
	 * because of the ItemInterface
	 * 
	 */
	@Override
	public void fertilize(int time) {
	}
}
