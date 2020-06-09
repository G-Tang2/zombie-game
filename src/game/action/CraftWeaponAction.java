package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Crafting a weapon from a zombie limb
 * 
 * @author Mike Kevin Balapitiya
 */
public class CraftWeaponAction extends Action {

	private Item item;

	/**
	 * Constructor.
	 *
	 * @param item the item to craft
	 */
	public CraftWeaponAction(Item item) {
		this.item = item;
	}

	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(item);
		Item newItem = item.upgrade();
		actor.addItemToInventory(newItem);
		return actor + " upgrades " + item + " to " + newItem;
	}

	/**
	 * Returns a descriptive string
	 * 
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " upgrades " + item;
	}

}
