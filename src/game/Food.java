package game;

import edu.monash.fit2099.engine.Action;

/**
 * Food for Humans to eat to restore health
 * 
 * @author Garvin Tang
 * 
 */
public class Food extends PortableItem {

	private int nutrition = 20;

	/**
     * Constructor.
     *
     * @see PortableItem#Food(Location)
     * 
     */
	public Food() {
		super("food", 'f');
		allowableActions.add(new EatAction(this)); // FIXME: remove this allowable action when dropped on ground
	}

	/**
     * Getter for food nutritional value (ammount of health it will heal).
     *
     * @return returns the nutrition value
     * 
     */
	public int getNutrition() {
		return nutrition;
	}

}
