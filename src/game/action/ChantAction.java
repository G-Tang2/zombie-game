package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ChantAction extends Action {

    private final int DURATION;
    private int counter = 0;

    public ChantAction(int duration) {
        DURATION = duration;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        counter++;
        return actor + " is chanting";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " chant";
    }

    @Override
    public Action getNextAction() {
        if (counter >= DURATION) {
            return new SummonZombieAction(5); // summon five zombies
        }
        return this;
    }

}