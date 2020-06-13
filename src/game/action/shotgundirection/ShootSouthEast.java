package game.action.shotgundirection;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.Shotgun;

class ShootSouthEast extends ShootDirection { // package-private

    ShootSouthEast(Shotgun weapon) { // package-private
        super(weapon, "south-east");
    }

    @Override
    String shoot(Actor actor, GameMap map, Location location) { // package-private
        String result = useAmmo();
        if (!result.isEmpty()) {
            return result;
        }
        for (int x = location.x(); x <= location.x() + range; x++) {
            for (int y = location.y(); y <= location.y() + range; y++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
        }
        result += endDescription(actor, result);
        return result;
    }

}