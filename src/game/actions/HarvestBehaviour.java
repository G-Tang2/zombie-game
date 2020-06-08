package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.behaviour.Behaviour;
import game.ground.CropCapability;

public class HarvestBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location location = exit.getDestination();
            if (location.getGround().hasCapability(CropCapability.RIPE)) {
                return new HarvestAction(location);
            }
        }
        return null;
    }
}