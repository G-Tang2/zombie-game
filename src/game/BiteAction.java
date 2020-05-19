package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Weapon;

public class BiteAction extends AttackAction {

    public BiteAction(Actor target) {
        super(target);
    }

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