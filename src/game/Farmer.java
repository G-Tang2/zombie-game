package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Farmer extends Human {

	private Behaviour behaviour = new WanderBehaviour();
	Random rand = new Random();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the farmer in the UI
	 * @param displayChar Character to represent the farmer in the UI
	 * @param hitPoints   Farmers's starting number of hitpoints
	 * @throws Exception
	 */
	public Farmer(String name) throws Exception {
		super(name, 'F', 50);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		boolean ableToSow = true;
		Location here = map.locationOf(this);

		if (here.getGround() instanceof Crop) {
			Actions allowableActions = here.getGround().allowableActions(this, map.locationOf(this), null);
			if (allowableActions.size() == 0) { // means the crop is not ripe
				return new FertilizeAction(here.getGround());
			}
		}
		for (Exit exit : here.getExits()) {
			Location location = exit.getDestination();
			if (ableToSow && location.getGround() instanceof Dirt) {
				if (rand.nextInt(100) < 33) {
					return new SowAction(location);
				} else {
					ableToSow = false; // this makes the farmer only have one chance to sow per turn
				}
			} else if (location.getGround() instanceof Crop) {
				Actions allowableActions = location.getGround().allowableActions(this, map.locationOf(this), null);
				if (allowableActions.size() > 0) { // crop is ripe and ready to harvest
					return allowableActions.get(rand.nextInt(allowableActions.size())); // this should only have the
																						// harvest action
				}
			}
		}

		try {
			return behaviour.getAction(this, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DoNothingAction();
	}

}
