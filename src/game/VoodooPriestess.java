package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class VoodooPriestess extends ZombieActor {

    private Behaviour[] behaviours = { new SummonBehaviour(10), new WanderBehaviour() };
    GameMap home;
    private final int STAY_PERIOD = 30;
    private int turnCounter = 0;

    public VoodooPriestess(String name) {
        super(name, 'V', 60, ZombieCapability.UNDEAD);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        this.turnCounter++;
        if (this.turnCounter >= STAY_PERIOD) {
            turnCounter = 0;
            return new VoodooLeaveAction(home);
        }
        // voodoo chanting
        if (lastAction != null && lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        // summons zombies or wander
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                return action;
            }
        }
        return new DoNothingAction();
    }

    public void setHome(GameMap home) {
        this.home = home;
    }

}