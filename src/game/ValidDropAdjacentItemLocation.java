package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

public class ValidDropAdjacentItemLocation {

    private Location location;
    private Actor actor;
    private Random rand = new Random();

    public ValidDropAdjacentItemLocation(Actor actor, Location location) {
        this.actor = actor;
        this.location = location;
    }

    public Location getValidLocation() {
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