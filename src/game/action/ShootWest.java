package game.action;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.Shotgun;

public class ShootWest extends ShootDirection {

    public ShootWest(Shotgun weapon) {
        super(weapon);
    }

    @Override
    public String shoot(Actor actor, GameMap map, Location location) {
        String result = useAmmo();
        if (result != null) {
            return result;
        }
        for (int x = location.x() - range; x <= location.x(); x++) {
            for (int y = location.y() - spread; y <= location.y() + spread; y++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
            spread--;
        }
        result += endDescription(actor, result);
        return result;
    }

}