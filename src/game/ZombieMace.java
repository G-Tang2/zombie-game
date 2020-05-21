package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A mace made from a zombie leg
 * 
 * @author Mike Kevin Balapitiya
 */
public class ZombieMace extends WeaponItem {
	/**
     * Constructor.
     * 
     * Creates a Mace
     * 
     * @param name        name of the limb e.g. "Mace"
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
	public ZombieMace(String name, char displayChar, int damage, String verb) {
		super(name, displayChar, damage, verb);
	}

}
