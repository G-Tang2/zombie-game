package game.action.shotgundirection;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.item.Shotgun;

/**
 * Shotgun menu for selection shooting direction.
 * 
 * @author Garvin Tang
 * @author Mike Kevin Balapitiya
 * 
 */
public class ShotgunMenu extends Action {
	private Shotgun weapon;

	/**
	 * Constructor.
	 * 
	 * @param weapon the weapon used
	 */
	public ShotgunMenu(Shotgun weapon) {
		this.weapon = weapon;

	}

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
