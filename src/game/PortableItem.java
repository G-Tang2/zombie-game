package game;

import java.util.Random;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Base class for any item that can be picked up and dropped.
 */
public class PortableItem extends Item {
	
	private Random rand = new Random();
	private int deathTime = 0;
	
	public PortableItem(String name, char displayChar) {
		super(name, displayChar, true);
	}
	
	public void tick(Location location) {
		super.tick(location);

		if (this.displayChar == '%') {
			deathTime++;
			if (deathTime >= 5) {
				int undeadChance = rand.nextInt(100);
				if (deathTime == 10 || undeadChance > 50) {
					// add zombie??
				}
			}
		}
	}

}
