package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.item.Sniper;

public class SniperMenu extends Action {

	private Actor target;
	private int targetInitHitPoints;
	private int aimTime;
	private Sniper weapon;
	private Actor actor;
	private Menu menu = new Menu();
	private boolean sniperShot = false;

	public SniperMenu(Actor actor, Actor target, Sniper weapon, int aimTime) {
		this.actor = actor;
		this.target = target;
		this.targetInitHitPoints = target.getHitPoints();
		this.weapon = weapon;
		this.aimTime = aimTime;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if (actor.getHitPoints() < this.targetInitHitPoints) {
			this.aimTime = 0;
		}
		Actions actions = new Actions();
		if (this.aimTime < 2) {
			actions.add(new AimAction(this.target));
		}
		actions.add(new SniperShootAction(this.target, this.weapon, this.aimTime));

		Action action = menu.showMenu(actor, actions, new Display());
		if (action instanceof AimAction) {
			this.aimTime++;
		} else if (action instanceof SniperShootAction) {
			sniperShot = true;
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
		if (sniperShot) {
			return null;
		}
		Actions actions = new Actions();
		actions.add(this);
		actions.add(new StopAimingAction(target));
		Action action = this.menu.showMenu(actor, actions, new Display());
		if (action instanceof StopAimingAction) {
			return null;
		}
		return action;
	}
}
