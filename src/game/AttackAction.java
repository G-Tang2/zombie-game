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

	String missDescription(Actor actor) {
		// NOTE: Used in both AttackAction and BiteAction
		return actor + " misses " + target + ".";
	}

	String attackTarget(Actor actor, GameMap map, Weapon weapon, int damage) {
		// NOTE: Used in both AttackAction and BiteAction
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (!target.isConscious()) {
			result += targetDeath(map);
		}
		return result;
	}

	String targetDeath(GameMap map) {

		Item corpse = new PortableItem("dead " + target, '%');
		map.locationOf(target).addItem(corpse);

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

	Item dropWeapon(Actions actions, GameMap map) {
		for (Item item : target.getInventory()) {
			if (item.asWeapon() != null) {
				new DropAdjacentItemAction(item).execute(target, map);
				return item;
			}
		}
		return null;
	}

	String dropLimbs(GameMap map) {
		int limbsLost = 0;
		int val = rand.nextInt(100);
		if (val > 15) {
			limbsLost = 1;
		} else if (val > 5) {
			limbsLost = 2;
		} else if (val > 1) {
			limbsLost = 3;
		} else {
			limbsLost = 4;
		}
		Actions actions = new Actions();
		Item droppedWeapon;
		String retVal = "";
		while (((Zombie) target).getLimbCount() > 0 && limbsLost > 0) {
			if (((Zombie) target).getLegCount() <= 0 || (((Zombie) target).getArmCount() > 0 && rand.nextBoolean())) {
				actions.add(new DropAdjacentItemAction(new ZombieLimb("Zombie arm", '~', 8, "slaps")));
				((Zombie) target).loseArm();
				if (((Zombie) target).getArmCount() == 0
						|| (((Zombie) target).getArmCount() == 1 && rand.nextBoolean())) {
					droppedWeapon = dropWeapon(actions, map);
					if (droppedWeapon != null) {
						retVal += System.lineSeparator() + target + " dropped their" + droppedWeapon;
					}
				}
			} else {
				actions.add(new DropAdjacentItemAction(new ZombieLimb("Zombie leg", '/', 10, "slaps")));
				((Zombie) target).loseLeg();
			}
			limbsLost--;
		}
		for (Action action : actions) {
			action.execute(target, map);
		}
		retVal += System.lineSeparator() + target + " lost " + actions.size() + " limbs!";

		return retVal;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		// 50% miss probability
		if (rand.nextBoolean()) {
			return missDescription(actor);
		}
		String result = attackTarget(actor, map, weapon, weapon.damage());

		if (target.hasCapability(ZombieCapability.UNDEAD) && ((Zombie) target).getLimbCount() > 0
				&& rand.nextInt(100) < 100) {
			result += dropLimbs(map);
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
