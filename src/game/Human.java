package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 * @throws Exception
	 */
	public Human(String name) throws Exception {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}

	/**
	 * The protected constructor can be used to create subtypes of Human, such as
	 * the Player
	 * 
	 * @param name        the human's display name
	 * @param displayChar character that will represent the Human in the map
	 * @param hitPoints   amount of damage that the Human can take before it dies
	 * @throws Exception
	 */
	protected Human(String name, char displayChar, int hitPoints) throws Exception {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from
		// zombies?

		// prioritise consuming food when hurt
		if (hitPoints < maxHitPoints) {
			Action action = searchForFood(map);
			if (action != null) {
				return action;
			}
		}
		try {
			return behaviour.getAction(this, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new DoNothingAction();
	}

	protected Action searchForFood(GameMap map) {
		Item item;
		// consume food if they have food in their inventory
		item = foodInInventory();
		if (item != null) {
			return new EatAction(item);
		}
		// pick up food if they are stand on top of it
		item = pickUpFood(map);
		if (item != null) {
			return new PickUpItemAction(item);
		}
		return null;
	}

	private Item foodInInventory() {
		for (Item item : inventory) {
			if (item instanceof Food) {
				return item;
			}
		}
		return null;
	}

	private Item pickUpFood(GameMap map) {
		for (Item item : map.locationOf(this).getItems()) {
			if (item instanceof Food) {
				return item;
			}
		}
		return null;
	}
}
