package game.item;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponItem;
import game.action.SniperMenu;

/**
 * Sniper.
 * 
 * @author Mike Kevin Balapitiya
 * 
 */
public class Sniper extends WeaponItem {
    protected int ammoCount;
    private boolean onGround = true;

    /**
     * Constructor.
     */
    public Sniper() {
        super("Sniper", 'S', 30, "snipes");
        this.ammoCount = 4;
    }

    @Override
    public int getNutrition() {
        return 0;
    }

    @Override
    public Item upgrade() {
        return null;
    }

    /*
     * Inform a carried Item of the passage of time.
     * 
     * This method is called once per turn, if the Item is being carried.
     * 
     * @param currentLocation The location of the actor carrying this Item.
     * 
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (onGround) {
            allowableActions.add(new SniperMenu(this, currentLocation.map(), actor));
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

    /**
     * Adds ammo to weapon.
     * 
     * @param ammo the amount of ammo to be added
     */
    public void addAmmo(int ammo) {
        if (this.ammoCount < 0) {
            this.ammoCount = ammo;
        } else {
            this.ammoCount += ammo;
        }
    }

    /**
     * Returns ammo count.
     * 
     * @return ammo count.
     */
    public int getAmmo() {
        return this.ammoCount;
    }

}
