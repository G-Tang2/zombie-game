package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

class StopAimingAction extends Action { // package-private

    private Actor target;

    StopAimingAction(Actor target) { // package-private
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " stops targeting " + target;
    }

}