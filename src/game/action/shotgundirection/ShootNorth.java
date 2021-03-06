package game.action.shotgundirection;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.item.Shotgun;

class ShootNorth extends ShootDirection { // package-private

    ShootNorth(Shotgun weapon) { // package-private
        super(weapon, "north");
    }

    @Override
    String shoot(Actor actor, GameMap map, Location location) { // package-private
        String result = useAmmo();
        if (!result.isEmpty()) {
            return result;
        }
        for (int y = location.y() - range; y <= location.y(); y++) {
            for (int x = location.x() - spread; x <= location.x() + spread; x++) {
                result += attackActorsInArea(actor, map, location, x, y);
            }
            spread--;
        }
        result += endDescription(actor, result);
        return result;
    }

}