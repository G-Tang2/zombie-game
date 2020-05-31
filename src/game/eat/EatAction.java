package game.eat;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Eat Action for a Human to consume food to recover health
 * 
 * @author Garvin Tang
 * 
 */
public class EatAction extends Action {

    private Item item;

    /**
     * Constructor.
     *
     * @see Action#EatAction(Item)
     * @param item the item is the food we want to eat
     */
    public EatAction(Item item) {
        this.item = item;
    }

    /**
     * Actor heals itself.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player ate food to restore health".
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        actor.heal(item.getNutrition());
        return actor + " ate " + item + " to restore " + item.getNutrition() + " health";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Fertilize crop".
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consume " + item;
    }

}