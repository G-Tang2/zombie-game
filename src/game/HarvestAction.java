package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Harvest action for harvesting a crop in the ground
 * 
 * @author Garvin Tang
 * 
 */
public class HarvestAction extends Action {

    private Location location;
    private Random rand = new Random();
    
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
        if (actor instanceof Farmer) {
            ArrayList<Location> validDropLocations = new ArrayList<Location>();

            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getGround().canActorEnter(actor)) {
                    validDropLocations.add(destination);
                }
            }
            validDropLocations.get(rand.nextInt(validDropLocations.size())).addItem(new Food());
        } else if (actor instanceof Player) {
            actor.addItemToInventory(new Food());
        }
        location.setGround(new Dirt());
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
        return "Harvest the ripe crop";
    }

}
