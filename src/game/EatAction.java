package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class EatAction extends Action {

    Item item;

    public EatAction(Item item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        actor.heal(item.getNutrition());
        return actor + " ate " + item + " to restore " + item.getNutrition() + " health";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Consume " + item;
    }

}