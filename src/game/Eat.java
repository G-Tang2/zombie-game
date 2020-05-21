package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class Eat extends Action {

    Food food;

    public Eat(Food food) {
        this.food = food;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(food);
        actor.heal(food.getNutrition());
        return actor + " ate " + food + " to restore health";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Consume " + food;
    }

}