package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Item;

/**
 * This interface provides the ability to add methods to Item, without modifying
 * code in the engine, or downcasting references in the game.
 */
public interface ItemInterface {
    /**
     * Returns food nutritional value
     * 
     * @return nutrition value of food
     */
    int getNutrition();

    /**
     * Upgrades weapon
     * 
     * @return an upgraded weapon
     */
    Item upgrade();

    /**
     * Adds ammo to weapon
     */
    void addAmmo(int ammo);

}
