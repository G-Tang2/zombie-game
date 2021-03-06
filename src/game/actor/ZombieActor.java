package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.action.AttackAction;

/**
 * Base class for Actors in the Zombie World
 * 
 * @author ram
 * @author Garvin Tang
 *
 */
public abstract class ZombieActor extends Actor {
	protected Actions actions = new Actions();
	protected int armCount = 2;
	protected int legCount = 2;

	/**
	 * The constructor that creates default actors.
	 * 
	 * @param name        the human's display name
	 * @param displayChar the character that represents the actor
	 * @param hitPoints   the health points of the actor
	 * @param team        identify if actor is alive or undead
	 */
	public ZombieActor(String name, char displayChar, int hitPoints, ZombieCapability team) {
		super(name, displayChar, hitPoints);
		addCapability(team);
		armCount = 2;
		legCount = 2;
	}

	/**
	 * Returns a collection of the Actions that the otherActor can do to the current
	 * Actor.
	 *
	 * @see Actor#getAllowableActions(Actor, String, GameMap)
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		if (otherActor.hasCapability(ZombieCapability.UNDEAD) != this.hasCapability(ZombieCapability.UNDEAD))
			list.add(new AttackAction(this));
		return list;
	}

	/**
	 * Executes actions the actor has stored.
	 * 
	 * @see ActionInterface#executeReaction(GameMap)
	 * @param map current GameMap
	 * @return a string that describes the actions executed
	 */
	@Override
	public String executeReaction(GameMap map) {
		String result = "";
		for (Action action : this.actions) {
			action.execute(this, map);
			result += System.lineSeparator() + action.menuDescription(this);
		}
		resetActions();
		return result;
	}

	private void resetActions() {
		this.actions = new Actions();
	}

	@Override
	public int getArmCount() {
		return armCount;
	}

	@Override
	public int getHitPoints() {
		return hitPoints;
	}
}
