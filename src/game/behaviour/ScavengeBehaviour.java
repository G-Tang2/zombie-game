package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * A class that generates a pick up item action at the actor's location if the
 * item is a weapon
 * 
 * @author Garvin Tang
 *
 */
public class ScavengeBehaviour implements Behaviour {
    /**
     * Returns a pick up item action if the item at the actor's location is a
     * weapon, else returns null
     * 
     * @see Behaviour#getAction(Actor, GameMap)
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Item item : map.locationOf(actor).getItems()) {
            if (item.asWeapon() != null) {
                return new PickUpItemAction(item);
            }
        }
        return null;
    }
}