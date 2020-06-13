package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.VoodooHome;
import game.action.VoodooLeaveAction;
import game.behaviour.Behaviour;
import game.behaviour.SummonBehaviour;
import game.behaviour.WanderBehaviour;

/**
 * Mambo Marie as Voodoo Priestess
 * 
 * @author Garvin Tang
 * 
 */
public class VoodooPriestess extends ZombieActor {

    private Behaviour[] behaviours = { new SummonBehaviour(10), new WanderBehaviour() };
    private VoodooHome home;
    private final int STAY_PERIOD = 30;
    private int turnCounter = 0;

    /**
     * Constructor.
     * 
     * @param name The name of VoodooPriestess.
     */
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

    /**
     * Set the home of VoodooPriestess
     * 
     * @param home A map were voodoo priestess goes when not in compound map.
     */
    public void setHome(VoodooHome home) {
        this.home = home;
    }

}