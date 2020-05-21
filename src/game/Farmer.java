package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Farmer extends Human {

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the farmer in the UI
	 * @param displayChar Character to represent the farmer in the UI
	 * @param hitPoints   Farmers's starting number of hitpoints
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		Location here = map.locationOf(this);

		for (Exit exit : here.getExits()) {
			Location location = exit.getDestination();
			if (location.getGround() instanceof Dirt) {
				return new SowAction(location);
			}
		}
		return null;
	}

}
