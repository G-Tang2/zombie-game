package game.item;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;
import game.action.CraftWeaponAction;

/**
 * A limb of a zombie
 * 
 * @author Garvin Tang
 */
public abstract class ZombieLimb extends WeaponItem {

    private boolean onGround = true;

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
    }

    public abstract Item upgrade();

    /**
     * Inform a carried Item of the passage of time.
     * 
     * This method is called once per turn, if the Item is being carried.
     * 
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (onGround) {
            allowableActions.add(new CraftWeaponAction(this));
            onGround = false;
        }
    }

    /**
     * Inform an Item on the ground of the passage of time. This method is called
     * once per turn, if the item rests upon the ground.
     * 
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (!onGround) {
            allowableActions = new Actions();
            onGround = true;
        }
    }

    /**
     * getter needed for foods which is not needed for this class but is required
     * because of the ItemInterface
     * 
     */
    @Override
    public int getNutrition() {
        return 0;
    }

    @Override
    public void addAmmo(int ammo) {
    }
}