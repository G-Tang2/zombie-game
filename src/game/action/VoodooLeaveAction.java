package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.VoodooHome;

/**
 * Voodoo leaving the compound map action.
 * 
 * @author Garvin Tang
 * 
 */
public class VoodooLeaveAction extends Action {

    private VoodooHome destination;

    /**
     * Constructor.
     * 
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
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
