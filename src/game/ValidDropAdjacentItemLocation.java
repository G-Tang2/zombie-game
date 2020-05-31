package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

public class ValidDropAdjacentItemLocation {

    private Random rand = new Random();

    public ValidDropAdjacentItemLocation() {
    }

    public Location getValidLocation(Actor actor, Location location) {
        ArrayList<Location> validDropLocations = new ArrayList<Location>();
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().canActorEnter(actor)) {
                validDropLocations.add(destination);
            }
        }
        if (validDropLocations.size() > 0) {
            return validDropLocations.get(rand.nextInt(validDropLocations.size()));
        }
        return null;
    }
}