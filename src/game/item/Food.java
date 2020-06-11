package game.item;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.action.EatAction;

/**
 * Food for Humans to eat to restore health
 * 
 * @author Garvin Tang
 * 
 */
public abstract class Food extends PortableItem {
	private int nutritionVal;
	private boolean onGround = true; // default value, cannot be false - would not allow player that harvest the crop
										// to not be able to eat it unless they drop and pick it up

	/**
	 * Constructor.
	 *
	 * @see PortableItem#Food(Location)
	 * 
	 */
	public Food(String name, char displayChar, int nutritionVal) {
		super(name, displayChar);
		this.nutritionVal = nutritionVal;
		addCapability(EatCapability.EDIBLE);
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
		if (onGround) {
			allowableActions.add(new EatAction(this)); // in inventory, can be consumed
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
			allowableActions = new Actions(); // on ground, cannot be consumed
			onGround = true;
		}
	}

	/**
	 * Getter for food nutritional value (ammount of health it will heal).
	 *
	 * @return returns the nutrition value
	 * 
	 */
	public int getNutrition() {
		return nutritionVal;
	}

}
