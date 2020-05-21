package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class SowAction extends Action {

    Location location;

    public SowAction(Location location) {
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.location.setGround(new Crop());
        return actor + " sowed a crop";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Sow a crop";
    }

}