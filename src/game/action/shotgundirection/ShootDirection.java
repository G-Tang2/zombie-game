package game.action.shotgundirection;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.action.AttackAction;
import game.item.Shotgun;

abstract class ShootDirection extends AttackAction { // package-private

    private Random rand = new Random();
    private Shotgun weapon;
    private String direction;
    protected int range = 3; // maximum distance the pellets reaches from the shooter
    protected int spread = 3; // maximum width the pellets spreads from the center

    ShootDirection(Shotgun weapon, String direction) { // package-private
        super(null); // there is no specific target aimed at
        this.weapon = weapon;
        this.direction = direction;
    }

    /**
     * Actor attacks target.
     *
     * @see AttackAction#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string, e.g. "Player attacks rock".
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return shoot(actor, map, map.locationOf(actor));
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

    abstract String shoot(Actor actor, GameMap map, Location location); // package-private

    protected String useAmmo() {
        if (this.weapon.getAmmo() <= 0) {
            return "Shotgun out of ammo";
        } else {
            this.weapon.addAmmo(-1); // decrement ammo count
        }
        return "";
    }

    protected String endDescription(Actor actor, String str) {
        String result = str.isEmpty() ? actor + " shot at nothing\n" : ""; // empty result means nothing was shot
                                                                           // at
        result += "Shotgun has " + weapon.getAmmo() + " ammo left";
        return result;
    }

    protected String attackActorsInArea(Actor actor, GameMap map, Location location, int x, int y) {
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
