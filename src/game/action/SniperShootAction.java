package game.action;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

/**
 * Bite action for attacking other Actors.
 * 
 * @author ram
 * @author Garvin Tang
 * 
 */
public class SniperShootAction extends AttackAction {

    private Weapon weapon;
    private int aimTime;
    private int damage;

    /**
     * Constructor.
     * 
     * Sets attack target
     * 
     * @param target The actor being targetted
     */
    public SniperShootAction(Actor target, Weapon weapon, int aimTime) {
        super(target);
        this.weapon = weapon;
        this.aimTime = aimTime;
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
        int missProbability;
        if (aimTime == 0) {
            missProbability = 25;
            this.damage = this.weapon.damage();
        } else if (aimTime == 1) {
            missProbability = 10;
            this.damage = this.weapon.damage() * 2;
        } else {
            missProbability = 0;
            this.damage = this.target.getHitPoints();
        }
        if (rand.nextInt(100) < missProbability) {
            return missDescription(actor);
        }
        String result = attackTarget(actor, map, this.weapon);
        return result;
    }

    @Override
    String attackTarget(Actor actor, GameMap map, Weapon weapon) { // default visibility
        String result = actor + " " + weapon.verb() + " " + target + " for " + this.damage + " damage.";

        target.hurt(this.damage);
        result += target.executeReaction(map); // execute target reactions from receiving damage
        if (!target.isConscious()) {
            result += targetDeath(map);
        }
        return result;
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player attacks rock".
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " " + weapon.verb() + " " + target;
    }
}