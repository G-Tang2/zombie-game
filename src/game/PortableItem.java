package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {
	int nutrition = 0;

	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}

	@Override
	public int getNutrition() {
		return nutrition;
	}
}
