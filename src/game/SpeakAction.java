package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SpeakAction extends Action {

    String speech;
    String[] verbs = { "moans", "grumbles", "snarls", "hisses", "growls", "grunts" };
    Random rand = new Random();

    public SpeakAction(String s) {
        speech = s;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + " " + verbs[rand.nextInt(verbs.length)] + " " + speech;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " says " + speech;
    }

}