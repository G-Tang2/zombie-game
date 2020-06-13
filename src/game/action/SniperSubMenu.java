package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.item.Sniper;

/**
 * Sniper submenu for selecting aim or shoot at target.
 * 
 * @author Garvin Tang
 * 
 */
public class SniperSubMenu extends Action {

	private Actor target;
	private int targetInitHitPoints;
	private int aimTime;
	private Sniper weapon;
	private Actor actor;
	private Menu menu = new Menu();
	private boolean sniperShot = false;

	/**
	 * Constructor
	 * 
	 * @param actor   The actor performing the action.
	 * @param target  The actor being attacked.
	 * @param weapon  The weapon used to attack target.
	 * @param aimTime The current number of turns aiming.
	 */
	public SniperSubMenu(Actor actor, Actor target, Sniper weapon, int aimTime) {
		this.actor = actor;
		this.target = target;
		this.targetInitHitPoints = target.getHitPoints();
		this.weapon = weapon;
		this.aimTime = aimTime;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.getHitPoints() < this.targetInitHitPoints) {
			// if actor gets hurt, aim time resets
			this.aimTime = 0;
		}
		Actions actions = new Actions();
		if (this.aimTime < 2) {
			// aim action
			actions.add(new AimAction(this.target));
		}
		// fire action
		actions.add(new SniperShootAction(this.target, this.weapon, this.aimTime));

		Action action = menu.showMenu(actor, actions, new Display());
		if (action instanceof AimAction) {
			// continue aiming
			this.aimTime++;
		} else if (action instanceof SniperShootAction) {
			// fire sniper
			sniperShot = true; // overhead to keep track if last turn was a sniper shot
		}
		String result = action.execute(actor, map);

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " targets " + this.target;
	}

	@Override
	public Action getNextAction() {
		// previous action was a fired sniper this object should be destroyed
		if (sniperShot) {
			return null;
		}
		Actions actions = new Actions();
		actions.add(this); // action to continue aiming or fire sniper
		actions.add(new StopAimingAction(target)); // action to stop aiming at a target and do another action
		Action action = this.menu.showMenu(actor, actions, new Display());
		if (action instanceof StopAimingAction) {
			return null;
		}
		return action;
	}
}
