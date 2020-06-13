package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.action.EatAction;
import game.item.EatCapability;

/**
 * Eat behaviour.
 * 
 * @author Garvin Tang
 * 
 */
public class EatBehaviour implements Behaviour {

    private boolean missingHealth;

    /**
     * Constructor.
     * 
     * @param missingHealth indicates if actor is missing health.
     */
    public EatBehaviour(boolean missingHealth) {
        this.missingHealth = missingHealth;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (missingHealth) {
            for (Item item : actor.getInventory()) {
                if (item.hasCapability(EatCapability.EDIBLE)) {
                    return new EatAction(item);
                }
            }
        }
        return null;
    }
}