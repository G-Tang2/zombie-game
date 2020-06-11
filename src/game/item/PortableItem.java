package game.item;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * Base class for any item that can be picked up and dropped.
 * 
 * @author Garvin Tang
 * 
 */
public class PortableItem extends Item {
     private int nutrition = 0;

     /**
      * Constructor.
      *
      * @see Item#PortableItem(String, char)
      * @param name        name of the item
      * @param displayChar character to be displayed on the map for the item
      */
     public PortableItem(String name, char displayChar) {
          super(name, displayChar, true);
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

	@Override
	public void addAmmo(int ammo) {
		
	}
}
