package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Farmer Actor who extends Humans and can grow and fertilise crops
 * 
 * @author Garvin Tang
 * 
 */
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
		addCapability(ActorCapability.HARVEST);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		boolean ableToSow = true;
		Location here = map.locationOf(this);

		// prioritise consuming food when hurt
		if (hitPoints < maxHitPoints) {
			Action action = searchForFood(map);
			if (action != null) {
				return action;
			}
		}
		// Farmer fertilising ground if there is a crop there
		if (here.getGround().hasCapability(CropCapability.UNRIPE)) {
			return new FertilizeAction(here.getGround());
		}
		// 33% chance when a farmer is standing on a dirt ground to turn it into a crop
		for (Exit exit : here.getExits()) {
			Location location = exit.getDestination();
			if (ableToSow && location.getGround().hasCapability(GroundCapability.SOWABLE)) {
				if (rand.nextInt(100) < 33) {
					return new SowAction(location);
				} else {
					ableToSow = false; // this makes the farmer only have one chance to sow per turn
				}
			} else if (location.getGround().hasCapability(CropCapability.RIPE)) {
				return new HarvestAction(location);
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
