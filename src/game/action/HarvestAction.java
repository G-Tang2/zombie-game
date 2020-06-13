package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actor.ActorCapability;
import game.ground.Dirt;
import game.item.Spinach;

/**
 * Harvest action for harvesting a crop in the ground
 * 
 * @author Garvin Tang
 * 
 */
public class HarvestAction extends Action {

    private Location location;

    /**
     * Constructor.
     *
     * @see Action#HarvestAction(Location)
     * @param location the location to harvest the crop
     */
    public HarvestAction(Location location) {
        this.location = location;
    }

    /**
     * Actor harvests crop into ground.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player harvested the ripe crop".
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(ActorCapability.DROPS_HARVEST)) {
            Location here = map.locationOf(actor); // current location of actor
            Location validDropLocation = new ValidDropAdjacentItemLocation().getValidLocation(actor, here); // get a
                                                                                                            // valid
                                                                                                            // adjacent
                                                                                                            // drop
                                                                                                            // location
            if (validDropLocation == null) { // no valid drop location
                return actor + " could not harvest the crop as there is no valid drop location";
            }
            validDropLocation.addItem(new Spinach("Spinach", 's', 20));
        } else if (actor.hasCapability(ActorCapability.POCKETS_HARVEST)) { // actor pockets the spinach
            actor.addItemToInventory(new Spinach("Spinach", 's', 20));
        }
        location.setGround(new Dirt()); // harvested crop returns to dirt
        return actor + " harvested the ripe crop";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Harvest the ripe crop".
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvest the ripe crop";
    }

}
