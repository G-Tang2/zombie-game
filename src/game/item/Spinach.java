package game.item;

/**
 * A food, spinach.
 * 
 * @author Garvin Tang
 * 
 */
public class Spinach extends Food {

    /**
     * Constructor.
     * 
     * @param name         name of food.
     * @param displayName  display name of food.
     * @param nutritionVal amount of hit points it restores.
     */
    public Spinach(String name, char displayName, int nutritionVal) {
        super(name, displayName, nutritionVal);
    }
}