package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.item.Shotgun;

public class ShotgunMenu extends Action {
	protected Shotgun weapon;
	protected Random rand = new Random();
	protected Actor target;

	/**
	 * Constructor.
	 * 
	 * @param shotgun the Actor to attack
	 */
	public ShotgunMenu(Shotgun shotgun) {
		this.weapon = shotgun;
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
		String[] directions = { "north", "north-east", "east", "south-east", "south", "south-west", "west",
				"north-west" };
		Actions actions = new Actions();
		for (String direction : directions) {
			actions.add(new ShotgunShootAction(weapon, direction));
		}

		Menu menu = new Menu();
		Action action = menu.showMenu(actor, actions, new Display());
		String result = action.execute(actor, map);
		return result;

	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " fires their shotgun";
	}
}
