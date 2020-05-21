package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A limb of a zombie
 * 
 * @author Garvin Tang
 */
public class ZombieLimb extends WeaponItem {

    /**
     * Constructor.
     * 
     * Creates a zombie limb
     * 
     * @param name        name of the limb e.g. "Zombie arm"
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     */
    public ZombieLimb(String name, char displayChar, int damage, String verb) {
        super(name, displayChar, damage, verb);
        allowableActions.add(new CraftWeaponAction(this));
    }

}