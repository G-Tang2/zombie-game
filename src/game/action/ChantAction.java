package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ChantAction extends Action {

    private int duration;

    public ChantAction(int duration) {
        this.duration = duration;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.duration--;
        return actor + " is chanting";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " chant";
    }

    @Override
    public Action getNextAction() {
        if (this.duration <= 0) {
            return new SummonZombieAction(5); // summon five zombies
        }
        return this;
    }

}