package game.action;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

class ValidDropAdjacentItemLocation { // package-private

    private Random rand = new Random();

    ValidDropAdjacentItemLocation() { // package-private
    }

    Location getValidLocation(Actor actor, Location location) { // package-private
        ArrayList<Location> validDropLocations = new ArrayList<Location>();
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().canActorEnter(actor)) { // if an actor can enter the location then the item can
                                                                // be dropped there
                validDropLocations.add(destination);
            }
        }
        if (validDropLocations.size() > 0) { // if there are any valid drop locations
            return validDropLocations.get(rand.nextInt(validDropLocations.size()));
        }
        return null;
    }
}