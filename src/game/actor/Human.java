package game.actor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.behaviour.Behaviour;
import game.behaviour.EatBehaviour;
import game.behaviour.PickUpFoodBehaviour;
import game.behaviour.WanderBehaviour;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 * @author Garvin Tang
 *
 */
public class Human extends ZombieActor {
	private Behaviour[] behaviours = { new EatBehaviour(this.hitPoints < this.maxHitPoints), new PickUpFoodBehaviour(),
			new WanderBehaviour() };

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, 'H', 50, ZombieCapability.ALIVE);
	}

	/**
	 * The protected constructor can be used to create subtypes of Human, such as
	 * the Player
	 * 
	 * @param name        the human's display name
	 * @param displayChar character that will represent the Human in the map
	 * @param hitPoints   amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	/**
	 * If a Human is hurt it will attempt to pick up food and eat it or else it will
	 * wander around.
	 * 
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map        the map where the current Zombie is
	 * @param display    the Display where the Zombie's utterances will be displayed
	 */

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				return action;
			}
		}
		return new DoNothingAction();
	}
}
