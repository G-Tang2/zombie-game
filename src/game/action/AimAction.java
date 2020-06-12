package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action that doesn't do anything.
 * 
 * Use this to implement waiting or similar actions in game clients.
 */
public class AimAction extends Action {

	private Actor target;

	public AimAction(Actor target) {
		this.target = target;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " aims at " + target;
	}

}
