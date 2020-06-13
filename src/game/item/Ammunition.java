package game.item;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.action.ReloadAction;

/**
 * Ammunition for ranged weapons.
 * 
 * @author Mike Kevin Balapitiya
 * 
 */
public abstract class Ammunition extends PortableItem {
	/**
	 * Constructor.
	 * 
	 * @param name        name of ammunition.
	 * @param displayChar display character
	 */
	public Ammunition(String name, char displayChar) {
		super(name, displayChar);
	}

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
		for (Item item : actor.getInventory()) {
			if ((item instanceof Shotgun) || (item instanceof Sniper)) {
				allowableActions = new Actions(new ReloadAction(item, this)); // in inventory, can reload a gun
			}
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
		allowableActions = new Actions(); // on ground, cannot reload
	}

	/**
	 * Returns the ammo count for weapon
	 */
	public abstract int getAmmo();

}
