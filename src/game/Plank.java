package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A primitive weapon.
 * 
 * @author ram
 *
 */
public class Plank extends WeaponItem {

	int nutrition = 0;

	public Plank() {
		super("plank", ')', 20, "whacks");
		// TODO Auto-generated constructor stub
	}

	/**
     * getter needed for foods which is not needed for this class
     * but is required because of the ItemInterface
     * 
     */
	@Override
	public int getNutrition() {
		return nutrition;
	}

}
