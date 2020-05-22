package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Sow action for planting a crop in the ground
 * 
 * @author Garvin Tang
 * 
 */
public class SowAction extends Action {

    Location location;

    /**
     * Constructor.
     *
     * @see Action#SowAction(Location)
     * @param location the location to plant the crop
     */
    public SowAction(Location location) {
        this.location = location;
    }

    /**
     * Actor sows crop into ground.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player sowed a crop".
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.location.setGround(new Crop());
        return actor + " sowed a crop";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Sow a crop".
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Sow a crop";
    }

}