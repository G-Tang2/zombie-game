package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.actor.ZombieCapability;
import game.item.HumanCorpse;
import game.item.PortableItem;

/**
 * Special Action for attacking other Actors.
 * 
 * @author ram
 * @author Garvin Tang
 * 
 */
public class AttackAction extends Action {

	protected Actor target;
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
	}

	/**
	 * Actor attacks target.
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a string, e.g. "Player attacks rock".
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return missDescription(actor);
		}
		String result = attackTarget(actor, map, weapon);

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
		return actor + " melees " + target;
	}

	protected String missDescription(Actor actor) {
		return actor + " misses " + target + ".";
	}

	protected String attackTarget(Actor actor, GameMap map, Weapon weapon) { // default visibility
		String result = actor + " " + weapon.verb() + " " + target + " for " + weapon.damage() + " damage.";

		target.hurt(weapon.damage());
		result += target.executeReaction(map); // execute target reactions from receiving damage
		if (!target.isConscious()) {
			result += targetDeath(map);
		}
		return result;
	}

	String targetDeath(GameMap map) {

		if (target.hasCapability(ZombieCapability.ALIVE)) {
			map.locationOf(target).addItem(new HumanCorpse("Dead " + target, 'x'));
		} else {
			map.locationOf(target).addItem(new PortableItem("Dead" + target, '%'));
		}

		// corpse drop items and removed from map
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)
			drop.execute(target, map);
		map.removeActor(target);

		String result = System.lineSeparator() + target + " is killed.";
		return result;
	}
}
