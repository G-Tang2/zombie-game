package game.actors;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

public class SpawnActor {

    Random rand = new Random();

    public SpawnActor() {
    };

    public boolean spawn(String name, Location location) {
        Zombie zombie = new Zombie(name);
        Location spawnLocation = location;
        if (spawnLocation.containsAnActor()) {
            // spawn zombie in an adjacent location
            spawnLocation = randAdjacentLocation(zombie, location);
            if (spawnLocation == null) {
                return false;
            }
        }
        spawnLocation.addActor(zombie);
        return true;
    }

    private Location randAdjacentLocation(Zombie actor, Location location) {
        ArrayList<Location> validDropLocations = new ArrayList<Location>();

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                validDropLocations.add(destination);
            }
        }

        if (validDropLocations.size() > 0) {
            return validDropLocations.get(rand.nextInt(validDropLocations.size()));
        }
        return null;
    }
}