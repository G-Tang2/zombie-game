package game.item;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

/**
 * A vehicle to move between maps.
 * 
 * @author Mike Kevin Balapitiya
 * 
 */
public class Car extends Item {

	public Car() {
		super("Car", '=', false);
	}

	/**
	 * Adds an action to car allowable actions.
	 * 
	 * @param action action to add
	 */
	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

	@Override
	public int getNutrition() {
		return 0;
	}

	@Override
	public Item upgrade() {
		return null;
	}

	@Override
	public void addAmmo(int ammo) {
	}
}
