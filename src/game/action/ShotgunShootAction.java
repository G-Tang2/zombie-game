package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.Shotgun;

/**
 * Bite action for attacking other Actors.
 * 
 * @author ram
 * @author Garvin Tang
 * 
 */
public class ShotgunShootAction extends AttackAction {

    private Shotgun weapon;
    private String direction;

    /**
     * Constructor.
     * 
     * Sets attack target
     * 
     * @param target The actor being targetted
     */
    protected ShotgunShootAction(Shotgun weapon, String direction) {
        super(null); // no specific target
        this.weapon = weapon;
        this.direction = direction;

    }

    /**
     * Actor bites target.
     *
     * @see AttackAction#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player bites rock".
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.weapon.getAmmo() <= 0) {
            return "Shotgun out of ammo";
        } else {
            this.weapon.addAmmo(-1);
        }
        Location here = map.locationOf(actor);
        String result = "";
        switch (direction) {
            case "north":
                result = north(actor, map, here);
                break;
            case "north-east":
                result = diagonalShot(actor, map, here, 1, 1, 0, 0);
                break;
            case "east":
                result = east(actor, map, here);
                break;
            case "south-east":
                result = diagonalShot(actor, map, here, 0, 1, 1, 0);
                break;
            case "south":
                result = south(actor, map, here);
                break;
            case "south-west":
                result = diagonalShot(actor, map, here, 0, 0, 1, 1);
                break;
            case "west":
                result = west(actor, map, here);
                break;
            case "north-west":
                result = diagonalShot(actor, map, here, 1, 0, 0, 1);
                break;
        }
        result = result.isEmpty() ? actor + " shot at nothing\n" : result; // empty result means nothing was shot at
        result += "Shotgun has " + weapon.getAmmo() + " ammo left";

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
        return actor + " " + weapon.verb() + " " + direction;
    }

    private String diagonalShot(Actor actor, GameMap map, Location location, int north, int east, int south, int west) {
        String result = "";

        for (int x = location.x() - (3 * west); x <= location.x() + (3 * east); x++) {
            for (int y = location.y() - (3 * north); y <= location.y() + (3 * south); y++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
        }
        return result;
    }

    private String north(Actor actor, GameMap map, Location location) {
        String result = "";
        int count = 3;

        for (int y = location.y() - 3; y <= location.y(); y++) {
            for (int x = location.x() - count; x <= location.x() + count; x++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
            count--;
        }
        return result;
    }

    private String south(Actor actor, GameMap map, Location location) {
        String result = "";
        int count = 3;

        for (int y = location.y() + 3; y >= location.y(); y--) {
            for (int x = location.x() - count; x <= location.x() + count; x++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
            count--;
        }
        return result;
    }

    private String east(Actor actor, GameMap map, Location location) {
        String result = "";
        int count = 3;

        for (int x = location.x() + 3; x >= location.y(); x--) {
            for (int y = location.y() - count; y <= location.y() + count; y++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
            count--;
        }
        return result;
    }

    private String west(Actor actor, GameMap map, Location location) {
        String result = "";
        int count = 3;

        for (int x = location.x() - 3; x <= location.y(); x++) {
            for (int y = location.y() - count; y <= location.y() + count; y++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
            count--;
        }
        return result;
    }

    private String attackActorsInArea(Actor actor, GameMap map, Location location, int x, int y) {
        String result = "";
        if (map.isAnActorAt(map.at(x, y)) && !(x == location.x() && y == location.y())) {
            target = map.getActorAt(map.at(x, y));
            if (rand.nextInt(100) < 25) {
                return missDescription(actor) + "\n";
            } else {
                result += attackTarget(actor, map, weapon) + "\n";
            }
        }
        return result;
    }
}