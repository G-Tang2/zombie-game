package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;

public class BiteAction extends AttackAction {

    public BiteAction(Actor target) {
        super(target);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = ((Zombie) actor).getBitingWeapon(); // TODO: Remove Zombie downcast

        // 70% miss probability
        if (rand.nextInt(100) < 70) {
            return missDescription(actor);
        }

        String result = attackTarget(actor, map, weapon, weapon.damage());
        actor.heal(5);
        System.out.println(actor.getHitPoints());

        return result;
    }
}