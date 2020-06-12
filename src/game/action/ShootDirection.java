package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.Shotgun;

public abstract class ShootDirection extends AttackAction {

    private Random rand = new Random();
    private Shotgun weapon;
    protected int range = 3; // maximum distance the pellets reaches from the shooter
    protected int spread = 3; // maximum width the pellets spreads from the center

    public ShootDirection(Shotgun weapon) {
        super(null); // there is no specific target aimed at
        this.weapon = weapon;
    }

    public abstract String shoot(Actor actor, GameMap map, Location location);

    protected String useAmmo() {
        if (this.weapon.getAmmo() <= 0) {
            return "Shotgun out of ammo";
        } else {
            this.weapon.addAmmo(-1);
        }
        return "";
    }

    protected String endDescription(Actor actor, String str) {
        String result = str.isEmpty() ? actor + " shot at nothing\n" : str; // empty result means nothing was shot
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

    @Override
    public String execute(Actor actor, GameMap map) {
        return shoot(actor, map, map.locationOf(actor));
    }
}