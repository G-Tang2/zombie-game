package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.action.FertilizeAction;
import game.ground.CropCapability;

public class FertilizeBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return map.locationOf(actor).getGround().hasCapability(CropCapability.UNRIPE)
                ? new FertilizeAction(map.locationOf(actor).getGround())
                : null;
    }
}