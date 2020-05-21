package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Crop extends Ground {

	private int ripeTime = 20;
	boolean ripe = false;

	public Crop() {
		super('c');
	}

	@Override
	public void tick(Location location) {
		super.tick(location);
		if (!ripe) {
			ripeTime--;
			if (ripeTime <= 0) {
				ripe = true;
				displayChar = 'C';
			}
		}
	}

	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		if (ripe) {
			actions.add(new HarvestAction(location));
		}
		return actions;
	}

	protected void fertilize(int time) {
		ripeTime = Math.max(ripeTime - time, 0);
	}
}
