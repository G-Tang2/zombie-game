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

        if (rand.nextInt(100) < 30) {
            return missDescription(actor);
        }

        int damage = weapon.damage();
        actor.heal(5);
        String result = attackDescription(actor, weapon, damage);

        result += dealDamage(map, damage);

        return result;
    }
}