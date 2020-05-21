package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

public class FertilizeAction extends Action {

    private Ground ground;

    public FertilizeAction(Ground ground) {
        this.ground = ground;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ground.fertilize(10);
        return actor + " has fertilized the crop";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Fertilize crop";
    }

}
