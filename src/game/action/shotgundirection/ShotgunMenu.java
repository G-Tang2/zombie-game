package game.action.shotgundirection;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.item.Shotgun;

public class ShotgunMenu extends Action {
	private Shotgun weapon;

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
		ShootDirection[] directions = { new ShootNorth(weapon), new ShootNorthEast(weapon), new ShootEast(weapon),
				new ShootSouthEast(weapon), new ShootSouth(weapon), new ShootSouthWest(weapon), new ShootWest(weapon),
				new ShootNorthWest(weapon) };
		Actions actions = new Actions();
		for (ShootDirection direction : directions) {
			actions.add(direction);
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
