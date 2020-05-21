package game;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Harvest extends Action {

    private Location location;
    private Random rand = new Random();

    public Harvest(Location location) {
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Farmer) {
            ArrayList<Location> validDropLocations = new ArrayList<Location>();

            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    validDropLocations.add(destination);
                }
            }
            if (validDropLocations.size() > 0) {
                validDropLocations.get(rand.nextInt(validDropLocations.size())).addItem(new Food());
            }
        }
        this.location.setGround(new Dirt());
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        // TODO Auto-generated method stub
        return null;
    }

}
