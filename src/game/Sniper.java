package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;

public class Sniper extends WeaponItem {
	protected int ammoCount;
	private boolean onGround = true;
	
	public Sniper() {
		super("Sniper", 'S', 10, "Thunks");
		this.ammoCount = 0;
	}

	@Override
	public int getNutrition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item upgrade() {
		return null;
	}
	
	/**
     * Inform a carried Item of the passage of time.
     * 
     * This method is called once per turn, if the Item is being carried.
     * 
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (onGround) {
            allowableActions.add(new SniperAction(this));
            onGround = false;
        }
    }

    /**
     * Inform an Item on the ground of the passage of time. This method is called
     * once per turn, if the item rests upon the ground.
     * 
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (!onGround) {
            allowableActions = new Actions();
            onGround = true;
        }
    }
	

}
