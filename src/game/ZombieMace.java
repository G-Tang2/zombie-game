package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A mace made from a zombie leg
 * 
 * @author Mike Kevin Balapitiya
 */
public class ZombieMace extends WeaponItem {
     private int nutrition = 0;

     /**
      * Constructor.
      * 
      * Creates a Mace
      * 
      * @param name        name of the limb e.g. "Mace"
      * @param displayChar character to use for display when item is on the ground
      * @param damage      amount of damage this weapon does
      * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
      */
     public ZombieMace() {
          super("Mace", '?', 24, "bludgeons");
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
