package game.item;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A primitive weapon.
 * 
 * @author ram
 *
 */
public class Plank extends WeaponItem {

	private int nutrition = 0;

	public Plank() {
		super("plank", ')', 20, "whacks");
	}

	/**
	 * getter needed for foods which is not needed for this class but is required
	 * because of the ItemInterface
	 * 
	 */
	@Override
	public int getNutrition() {
		return nutrition;
	}

	@Override
	public Item upgrade() {
		return null;
	}

}
