package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A club made from a zombie arm
 * 
 * @author Mike Kevin Balapitiya
 */
public class ZombieClub extends WeaponItem {
	/**
     * Constructor.
     * 
     * Creates a Club
     * 
     * @param name        name of the limb e.g. "Club"
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
	public ZombieClub() {
		super("Club", 'p', 20, "thumps");
	}


}
