package game.action;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Weapon;

/**
 * Bite action for attacking other Actors.
 * 
 * @author ram
 * @author Garvin Tang
 * 
 */
public class BiteAction extends AttackAction {
    /**
     * Constructor.
     * 
     * Sets attack target
     * 
     * @param target The actor being targetted
     */
    public BiteAction(Actor target) {
        super(target);
    }

    /**
     * Actor bites target.
     *
     * @see AttackAction#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player bites rock".
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = new IntrinsicWeapon(12, "bites");

        if (rand.nextInt(100) < 70) {
            return missDescription(actor);
        }

        String result = attackTarget(actor, map, weapon);
        actor.heal(5);

        return result;
    }
}