package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.item.Ammunition;

/**
 * Reload weapon action.
 * 
 * @author Mike Kevin Balapitiya
 * 
 */
public class ReloadAction extends Action {

    private Item weapon;
    private Ammunition ammo;

    /**
     * Constructor.
     *
     * @see Action#ReloadAction(Item)
     * @param item the item is the weapon we want to reload
     */
    public ReloadAction(Item weapon, Ammunition ammo) {
        this.weapon = weapon;
        this.ammo = ammo;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        weapon.addAmmo(ammo.getAmmo());
        actor.removeItemFromInventory(ammo);
        return actor + " relaods " + weapon + " with " + ammo.getAmmo() + " bullets";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " reloads " + weapon;
    }

}
