package game.item;

import edu.monash.fit2099.engine.Item;

/**
 * A zombie arm.
 * 
 * @author Garvin Tang
 * 
 */
public class ZombieArm extends ZombieLimb {

	/**
	 * Returns a descriptive string
	 * 
	 * @param name        Name of zombie arm.
	 * @param displayChar Display character.
	 */
	public ZombieArm(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}

	@Override
	public Item upgrade() {
		return new ZombieClub();
	}

}