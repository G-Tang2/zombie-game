package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Crop extends Ground {
	
	private int ripeTime = 20;
	
	public Crop() {
		super('d');
	}
	
	@Override
	public void tick(Location location) {
		super.tick(location);

		ripeTime--;
		if (ripeTime <= 0) {
			
		}
	}
	

}
