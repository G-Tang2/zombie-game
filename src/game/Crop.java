package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Crop on the ground that will eventually
 * rippen into fruit
 * 
 * @author Garvin Tang
 */
public class Crop extends Ground {

	private int ripeTime = 20;
	boolean ripe = false;

	/**
	* Constructor.
	*
	* @see PortableItem#Food(Location)
	* 
	*/
	public Crop() {
		super('c');
	}

	/**
     * Crop experiences time through this tick method so
     * it will eventually ripen and can be harvested for
     * food
     *
     * @see Item#tick(Location)
     * @param location The location where the crop is to be made.
     */
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

	/**
     * Allows for the crop to be harvested by a Human after it
     * has ripened
     *
     * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return returns the harvest action allowing the crop to be harvested
     */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		Actions actions = new Actions();
		if (ripe) {
			actions.add(new HarvestAction(location));
		}
		return actions;
	}

	public void fertilize(int time) {
		ripeTime = Math.max(ripeTime - time, 0);
	}
}
