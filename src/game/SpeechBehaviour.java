package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Returns a speak action or null
 * 
 * @author Garvin Tang
 */
public class SpeechBehaviour implements Behaviour {

    private Random rand = new Random();

    /**
     * 10% chance to return a speak action.
     * 
     * @see Behaviour#getAction(Actor, GameMap)
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an speak action the actor will say
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (rand.nextInt(100) < 10) {
            return new SpeakAction("Braaaaains");
        }
        return null;
    }
}