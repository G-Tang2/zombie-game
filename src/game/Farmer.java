package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

/**
 * Farmer Actor who extends Humans and can grow and fertilise crops
 * 
 * @author Garvin Tang
 * 
 */
public class Farmer extends Human {

	private Behaviour[] behaviours = { new EatBehaviour(this.hitPoints < this.maxHitPoints), new PickUpFoodBehaviour(),
			new FertilizeBehaviour(), new SowBehaviour(), new HarvestBehaviour(), new WanderBehaviour() };

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the farmer in the UI
	 * @param displayChar Character to represent the farmer in the UI
	 * @param hitPoints   Farmers's starting number of hitpoints
	 */
	public Farmer(String name) {
		super(name, 'F', 50);
		addCapability(ActorCapability.DROPS_HARVEST);
	}

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
