package game.action.shotgundirection;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.Shotgun;

public class ShootNorthEast extends ShootDirection {

    public ShootNorthEast(Shotgun weapon) {
        super(weapon, "north-east");
    }

    @Override
    public String shoot(Actor actor, GameMap map, Location location) {
        String result = useAmmo();
        if (!result.isEmpty()) {
            return result;
        }
        for (int x = location.x(); x <= location.x() + range; x++) {
            for (int y = location.y() - range; y <= location.y(); y++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
        }
        result += endDescription(actor, result);
        return result;
    }

}