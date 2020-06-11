package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.item.EatCapability;

public class PickUpFoodBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Item item : map.locationOf(actor).getItems()) {
            if (item.hasCapability(EatCapability.EDIBLE)) {
                return item.getPickUpAction();
            }
        }
        return null;
    }
}