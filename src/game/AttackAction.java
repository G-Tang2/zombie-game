package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Boolean biteAttack = false;
		Weapon weapon = null;

		if (actor.hasCapability(ZombieCapability.UNDEAD)) {
			if (rand.nextBoolean()) {
				weapon = ((Zombie) actor).getBitingWeapon(); // TODO: Remove downcasting
				biteAttack = true;
			}
		}

		if (weapon == null) {
			weapon = actor.getWeapon();
		}

		String missDescription = actor + " misses " + target + ".";
		if (biteAttack && rand.nextDouble() <= 0.7) { // bite attack miss
			return missDescription;
		} else if (rand.nextBoolean()) { // normal attack miss
			return missDescription;
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (!target.isConscious()) {
			Item corpse = new PortableItem("dead " + target, '%');
			map.locationOf(target).addItem(corpse);

			// corpse drop items
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)
				drop.execute(target, map);
			map.removeActor(target);

			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
