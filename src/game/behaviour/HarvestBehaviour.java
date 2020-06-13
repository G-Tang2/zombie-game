package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.action.HarvestAction;
import game.behaviour.Behaviour;
import game.ground.CropCapability;

/**
 * Harvest behaviour
 * 
 * @author Garvin Tang
 * 
 */
public class HarvestBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // check actor location for ripe crop
        if (map.locationOf(actor).getGround().hasCapability(CropCapability.RIPE)) {
            return new HarvestAction(map.locationOf(actor));
        }
        // check adjacent location from actor for ripe crop
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location location = exit.getDestination();
            if (location.getGround().hasCapability(CropCapability.RIPE)) {
                return new HarvestAction(location);
            }
        }
        return null;
    }
}