package game.action.shotgundirection;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.Shotgun;

class ShootNorthWest extends ShootDirection { // package-private

    ShootNorthWest(Shotgun weapon) { // package-private
        super(weapon, "north-west");
    }

    @Override
    String shoot(Actor actor, GameMap map, Location location) { // package-private
        String result = useAmmo();
        if (!result.isEmpty()) {
            return result;
        }
        for (int x = location.x() - range; x <= location.x(); x++) {
            for (int y = location.y() - range; y <= location.y(); y++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
        }
        result += endDescription(actor, result);
        return result;
    }

}