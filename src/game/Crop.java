package game;

import edu.monash.fit2099.engine.Actions;
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
			}
		}
	}

	@Override
	public Actions allowableActions(Location location) {
		Actions actions = new Actions();
		if (ripe) {
			actions.add(new Harvest(location));
		}
		return actions;
	}

}
