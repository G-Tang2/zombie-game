package game.item;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.action.ReloadAction;

public abstract class Ammunition extends PortableItem {
	
	public Ammunition(String name, char displayChar) {
		super(name, displayChar);
		// TODO Auto-generated constructor stub
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
		for (Item item:actor.getInventory()) {
			if ((item instanceof Shotgun) || (item instanceof Sniper)) {
				allowableActions.add(new ReloadAction(item, this)); // in inventory, can reload a gun
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
	
	public abstract int getAmmo();

}
