package game.item;

import edu.monash.fit2099.engine.Item;

/**
 * A zombie leg.
 * 
 * @author Garvin Tang
 * 
 */
public class ZombieLeg extends ZombieLimb {

	public ZombieLeg(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}

	@Override
	public Item upgrade() {
		return new ZombieMace();
	}
}