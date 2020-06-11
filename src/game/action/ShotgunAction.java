package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Weapon;
import edu.monash.fit2099.engine.WeaponItem;
import game.actor.ZombieCapability;
import game.item.HumanCorpse;
import game.item.PortableItem;
import game.item.Shotgun;

public class ShotgunAction extends Action {
	protected int ammo = 7;
	protected WeaponItem weapon;
	protected Random rand = new Random();
	protected Actor target;
	
	/**
	 * Constructor.
	 * 
	 * @param shotgun the Actor to attack
	 */
	public ShotgunAction(Shotgun shotgun) {
		this.weapon = shotgun;
	}
	
	private String diagonalShot(Actor actor, GameMap map, int north, int east, int south, int west) {
		Location here = map.locationOf(actor);
		String result = "";
		for (int x = here.x()-(3*west); x <= here.x()+(3*east); x++) {
			for (int y = here.y()-(3*north); y <= here.y()+(3*south); y++) {
				if (map.isAnActorAt(map.at(x, y)) && !(x == here.x() && y == here.y())) {
					target = map.getActorAt(map.at(x, y));
					if (rand.nextInt(100) < 25) {
						return missDescription(actor);
					} 
					else {
						result += attackTarget(actor, map, weapon) + "\n";
					}
				}
			}
		}
		return result;
	}
	
	private String north(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		String result = "";
		int count = 3;
		
		for (int y = here.y()-3; y <= here.y(); y++) {
				for (int x = here.x()-count; x <= here.x()+count; x++) {
					if (map.isAnActorAt(map.at(x, y)) && !(x == here.x() && y == here.y())) {
						target = map.getActorAt(map.at(x, y));
						if (rand.nextInt(100) < 25) {
							return missDescription(actor);
						} 
						else {
							result += attackTarget(actor, map, weapon) + "\n";
						}
					}
				}
				count--;
		}
		return result;	
	}
	
	private String south(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		String result = "";
		int count = 3;
		
		for (int y = here.y()+3; y >= here.y(); y--) {
				for (int x = here.x()-count; x <= here.x()+count; x++) {
					if (map.isAnActorAt(map.at(x, y)) && !(x == here.x() && y == here.y())) {
						target = map.getActorAt(map.at(x, y));
						if (rand.nextInt(100) < 25) {
							return missDescription(actor);
						} 
						else {
							result += attackTarget(actor, map, weapon) + "\n";
						}
					}
				}
				count--;
		}
		return result;	
	}
	
	
	private String east(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		String result = "";
		int count = 3;
		
		for (int x = here.x()+3; x >= here.y(); x--) {
			
				for (int y = here.y()-count; y <= here.y()+count; y++) {
					if (map.isAnActorAt(map.at(x, y)) && !(x == here.x() && y == here.y())) {
						target = map.getActorAt(map.at(x, y));
						if (rand.nextInt(100) < 25) {
							return missDescription(actor);
						} 
						else {
							result += attackTarget(actor, map, weapon) + "\n";
						}
					}
				}
				count--;
		}
		return result;	
	}
	
	
	private String west(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		String result = "";
		int count = 3;
		
		for (int x = here.x()-3; x <= here.y(); x++) {
			for (int y = here.y()-count; y <= here.y()+count; y++) {
					if (map.isAnActorAt(map.at(x, y)) && !(x == here.x() && y == here.y())) {
						target = map.getActorAt(map.at(x, y));
						if (rand.nextInt(100) < 25) {
							return missDescription(actor);
						} 
						else {
							result += attackTarget(actor, map, weapon) + "\n";
						}
					}
				}
				count--;
		}
		return result;	
	}

	
	/**
	 * Actor attacks target.
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 * @return a string, e.g. "Player attacks rock".
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		if (this.ammo <= 0) {
			return "Shotgun out of ammo";
		}
		else {
			this.ammo--;
		}
		
		String[] directions = { "North", "North East", "East", "South East", "South", "South West", "West",
		"North West" };
		int count = 0;
		Display display = new Display();
		String result = "";
	
		display.println("Choose direction to shoot:");
		for (String direction:directions) {
			count++;
			display.println(Integer.toString(count) + ": " + direction);
		}
		
		char choice = display.readChar();
		
		switch (Character.getNumericValue(choice)) {
			case 1:
				result = north(actor, map);
				break;
			case 2:
				result = diagonalShot(actor, map, 1, 1, 0, 0);
				break;
			case 3:
				result = east(actor, map);
				break;
			case 4:
				result = diagonalShot(actor, map, 0, 1, 1, 0);
				break;
			case 5:
				result = south(actor, map);
				break;
			case 6:
				result = diagonalShot(actor, map, 0, 0, 1, 1);
				break;
			case 7:
				result = west(actor, map);
				break;
			case 8:
				result = diagonalShot(actor, map, 1, 0, 0, 1);
				break;
			}
		return result;
		
	}

	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return a string, e.g. "Player attacks rock".
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " shoots shotgun";
	}

	String missDescription(Actor actor) { // default visibility
		return actor + " misses " + target +".";
	}

	String attackTarget(Actor actor, GameMap map, Weapon weapon) { // default visibility
		String result = actor + " shotguns " + target + " for 80 damage.";

		target.hurt(80);
		result += target.executeReaction(map); // execute target reactions from receiving damage
		if (!target.isConscious()) {
			result += targetDeath(map);
		}
		return result;
	}

	private String targetDeath(GameMap map) {

		if (target.hasCapability(ZombieCapability.ALIVE)) {
			map.locationOf(target).addItem(new HumanCorpse("Dead " + target, 'x'));
		} else {
			map.locationOf(target).addItem(new PortableItem("Dead" + target, '%'));
		}

		// corpse drop items and removed from map
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)
			drop.execute(target, map);
		map.removeActor(target);

		String result = System.lineSeparator() + target + " is killed.";
		return result;
	}
}
