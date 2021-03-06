package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Quit action to stop game.
 * 
 * @author Garvin Tang
 */
public class Quit extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " packed his bag and called it a day";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Quit game";
    }

}
