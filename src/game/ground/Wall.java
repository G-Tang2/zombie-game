package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

/**
 * A wall of a house.
 * 
 * @author Mike Kevin Balapitiya
 *
 */
public class Wall extends Ground {

	public Wall() {
		super('|');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public void fertilize(int time) {
	}
}
