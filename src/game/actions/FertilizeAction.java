package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

/**
 * Fertilize action for fertilising a crop in the ground
 * 
 * @author Garvin Tang
 * 
 */
public class FertilizeAction extends Action {

    private Ground ground;

    /**
     * Constructor.
     *
     * @see Action#FertilizeAction(Ground)
     * @param ground the ground where the crop is to fertilise
     */
    public FertilizeAction(Ground ground) {
        this.ground = ground;
    }

    /**
     * Actor fertilises crop in ground.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player has fertilized the crop".
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ground.fertilize(10);
        return actor + " fertilized the crop";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Fertilize crop".
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fertilize crop";
    }

}
