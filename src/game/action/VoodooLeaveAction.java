package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.VoodooHome;

public class VoodooLeaveAction extends Action {

    VoodooHome destination;

    public VoodooLeaveAction(VoodooHome destination) {
        this.destination = destination;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        destination.moveActor(actor, destination.at(0, 0));
        return actor + " left the map";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " leave map";
    }

}