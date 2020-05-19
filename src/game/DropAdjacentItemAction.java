package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Special Action that allows Actors to drop items at an adjacent location.
 * 
 * @author Garvin Tang
 * 
 */
public class DropAdjacentItemAction extends DropItemAction {

    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @see DropItemAction#DropItemAction(Item)
     * @param item the item to drop
     */
    public DropAdjacentItemAction(Item item) {
        super(item);
    }

    /**
     * Drop the item at an adjacent location.
     *
     * @see DropItemAction#execute(Actor, GameMap)
     * @param actor The actor performing the action
     * @param map   The map the actor is on
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<Location> validDropLocations = new ArrayList<Location>();
        Location here = map.locationOf(actor);

        actor.removeItemFromInventory(item);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                validDropLocations.add(destination);
            }
        }
        validDropLocations.get(rand.nextInt(validDropLocations.size())).addItem(item);
        return menuDescription(actor);
    }
}