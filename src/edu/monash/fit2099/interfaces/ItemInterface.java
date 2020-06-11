package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * This interface provides the ability to add methods to Item, without modifying
 * code in the engine, or downcasting references in the game.
 */
public interface ItemInterface {
    int getNutrition();

    Item upgrade();
    
    void addAmmo(int ammo);
}
