package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

class AimAction extends Action { // package-private

	private Actor target;

	AimAction(Actor target) { // package-private
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
