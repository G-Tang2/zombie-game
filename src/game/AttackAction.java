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

	String targetDeath(GameMap map) {

		Item corpse = new PortableItem("dead " + target, '%');
		map.locationOf(target).addItem(corpse);

		// corpse drop items
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)
			drop.execute(target, map);
		map.removeActor(target);

		String result = System.lineSeparator() + target + " is killed.";
		return result;
	}

	String missDescription(Actor actor) {
		// NOTE: Used in both AttackAction and BiteAction
		return actor + " misses " + target + ".";
	}

	String attackDescription(Actor actor, Weapon weapon, int damage) {
		// NOTE: Used in both AttackAction and BiteAction
		return actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
	}

	String dealDamage(GameMap map, int damage) {
		String result = "";

		target.hurt(damage);
		if (!target.isConscious()) {
			result += targetDeath(map);
		}
		return result;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return missDescription(actor);
		}

		int damage = weapon.damage();
		String result = attackDescription(actor, weapon, damage);

		result += dealDamage(map, damage);

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
