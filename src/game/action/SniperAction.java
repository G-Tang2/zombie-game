package game.action;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import edu.monash.fit2099.engine.Weapon;
import game.actor.ZombieCapability;
import game.item.HumanCorpse;
import game.item.PortableItem;
import game.item.Sniper;

public class SniperAction extends Action {

	protected Item weapon;
	protected ArrayList<Actor> targets = new ArrayList<Actor>();
	protected Random rand = new Random();
	private Actor target;
	
	/**
	 * Constructor.
	 * 
	 * @param shotgun the Actor to attack
	 */
	public SniperAction(Sniper sniper) {
		this.weapon = sniper;
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
		Location here = map.locationOf(actor);
		Location there;
		
		NumberRange xs, ys;
		xs = new NumberRange(0, 80);
		ys = new NumberRange(0, 25);

		for (int x : xs) {
			for (int y : ys) {
				if (map.isAnActorAt(map.at(x, y))) {
					targets.add(map.getActorAt(map.at(x, y)));
				}
			}
		}
		
		for (int i = 0; i < targets.size(); i++) {
			there = map.locationOf(targets.get(i));
			if (here.x() == there.x() || here.y() == there.y()) {
				xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
				ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);
	
				for (int x : xs) {
					for (int y : ys) {
						if(map.at(x, y).getGround().blocksThrownObjects())
							targets.remove(i);

					}
				}
			}
		}
		
		Display display = new Display();
		
		display.println("Choose target:");
		for (int j = 0; j < targets.size(); j++) {
			display.println(Integer.toString(j) + ": " + targets.get(j));
		}
		
		char choice = display.readChar();
		this.target = targets.get(Integer.parseInt(Character.toString(choice)));
		
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
		return actor + " aims sniper";
	}

	String missDescription(Actor actor) { // default visibility
		return actor + " misses " + target + ".";
	}

	String attackTarget(Actor actor, GameMap map, Weapon weapon) { // default visibility
		String result = actor + " " + weapon.verb() + " " + target + " for " + weapon.damage() + " damage.";

		target.hurt(weapon.damage());
		result += target.executeReaction(map); // execute target reactions from receiving damage
		if (!target.isConscious()) {
			result += targetDeath(map);
		}
		return result;
	}

	private String targetDeath(GameMap map) {

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
