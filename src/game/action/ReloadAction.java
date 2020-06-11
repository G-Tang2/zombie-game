package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
import game.item.Ammunation;


public class ReloadAction extends Action {
	
	private WeaponItem weapon;
	private Ammunation ammo;
	

    /**
     * Constructor.
     *
     * @see Action#ReloadAction(Item)
     * @param item the item is the weapon we want to reload
     */
    public ReloadAction(WeaponItem weapon, Ammunation ammo) {
        this.weapon = weapon;
        this.ammo = ammo;
    }
	
	@Override
	public String execute(Actor actor, GameMap map) {
		actor.removeItemFromInventory(ammo);
		weapon.addAmmo(ammo.getAmmo());
        return actor + " relaods " + weapon + " with " + ammo.getNutrition() + " bullets";
    }
	

	@Override
	public String menuDescription(Actor actor) {
		return actor + " reloads " + weapon;
	}

}
