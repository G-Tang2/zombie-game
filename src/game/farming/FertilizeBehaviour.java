package game.farming;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;

public class FertilizeBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return map.locationOf(actor).getGround().hasCapability(CropCapability.UNRIPE)
                ? new FertilizeAction(map.locationOf(actor).getGround())
                : null;
    }
}