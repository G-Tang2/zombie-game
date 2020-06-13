package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.NumberRange;
import game.actor.ZombieCapability;
import game.item.Sniper;

/**
 * Sniper menu for selecting target.
 * 
 * @author Garvin Tang
 * 
 */
public class SniperMenu extends Action {

	protected Sniper weapon;
	protected Random rand = new Random();
	private Actor actor;
	private Action lastAction = null;

	/**
	 * Constructor.
	 * 
	 * @param shotgun the Actor to attack
	 */
	public SniperMenu(Sniper weapon, GameMap map, Actor actor) {
		this.weapon = weapon;
		this.actor = actor;
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
		Actions actions = findTargets(map);
		String result = "There are no targets to snipe";
		if (actions.size() > 0) {
			Menu menu = new Menu();
			Action action = menu.showMenu(actor, actions, new Display());
			this.lastAction = action;
			result = action.execute(actor, map);
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " snipe someone";
	}

	@Override
	public Action getNextAction() {
		return this.lastAction.getNextAction();
	}

	private Actions findTargets(GameMap map) {
		Actions actions = new Actions();
		NumberRange xs, ys;
		xs = new NumberRange(0, 80);
		ys = new NumberRange(0, 25);

		// search every location in map
		for (int x : xs) {
			for (int y : ys) {
				if (map.isAnActorAt(map.at(x, y))
						&& map.getActorAt(map.at(x, y)).hasCapability(ZombieCapability.UNDEAD)) { // if undead actor is
																									// at (x, y)
																									// location
					actions.add(new SniperSubMenu(this.actor, map.getActorAt(map.at(x, y)), this.weapon, 0)); // actor
																												// can
																												// be
																												// sniped
				}
			}
		}
		return actions;
	}
}
