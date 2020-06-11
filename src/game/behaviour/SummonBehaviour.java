package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.action.ChantAction;

public class SummonBehaviour implements Behaviour {
    private final int SUMMON_INTERVAL;
    private int turnCounter = 0;

    public SummonBehaviour(int interval) {
        SUMMON_INTERVAL = interval;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        turnCounter++;
        if (turnCounter >= SUMMON_INTERVAL) {
            turnCounter = 0;
            return new ChantAction(1);
        } else
            return null;
    }

}