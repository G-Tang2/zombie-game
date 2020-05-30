package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class EatBehaviour implements Behaviour {

    boolean missingHealth;

    public EatBehaviour(boolean missingHealth) {
        this.missingHealth = missingHealth;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (missingHealth) {
            for (Item item : actor.getInventory()) {
                if (item.hasCapability(EatCapability.EDIBLE)) {
                    return new EatAction(item);
                }
            }
        }
        return null;
    }
}