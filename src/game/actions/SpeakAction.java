package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A special action for speaking
 * 
 * @author Garvin Tang
 */
public class SpeakAction extends Action {

    private String speech;
    private String[] verbs = { "moans", "grumbles", "snarls", "hisses", "growls", "grunts" };
    private Random rand = new Random();

    /**
     * Constructor.
     * 
     * Sets the sentence of the speech.
     * 
     * @param speech a sentence that will be said
     */
    public SpeakAction(String speech) {
        this.speech = speech;
    }

    /**
     * Actor says something.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player hisses kill".
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " " + verbs[rand.nextInt(verbs.length)] + " " + speech;
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player says kill".
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " says " + speech;
    }

}