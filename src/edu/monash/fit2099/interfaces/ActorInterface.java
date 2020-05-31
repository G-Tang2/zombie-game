package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.GameMap;

/**
 * This interface provides the ability to add methods to Actor, without
 * modifying code in the engine, or downcasting references in the game.
 */

public interface ActorInterface {

    /**
     * Executes actions the actor has stored.
     * 
     * @param map current GameMap
     * @return a string that describes the actions executed
     */
    String executeReaction(GameMap map);

    /**
     * Returns actor arm count.
     * 
     * @return actor arm count
     */
    int getArmCount(); // required for AttackBehaviour
}
