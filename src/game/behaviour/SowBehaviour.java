package game.behaviour;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.action.SowAction;
import game.ground.GroundCapability;

/**
 * Sow behaviour.
 * 
 * @author Garvin Tang
 * 
 */
public class SowBehaviour implements Behaviour {

    private Random rand = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location location = exit.getDestination();
            if (location.getGround().hasCapability(GroundCapability.SOWABLE)) {
                if (rand.nextInt(100) < 33) {
                    return new SowAction(location);
                } else {
                    break; // has one attempt at sowing per turn
                }
            }
        }
        return null;
    }

}