package game.action;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import game.actor.Zombie;

/**
 * Spawns a Zombie actor
 * 
 * @author Garvin Tang
 * 
 */
public class SpawnActor {

    private Random rand = new Random();

    /**
     * Constructor.
     * 
     * @param name     name of actor being spawned
     * @param location spawn location
     */
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
            if (destination.canActorEnter(actor)) { // if an actor can enter this location, then an item can be dropped
                                                    // there
                validDropLocations.add(destination);
            }
        }

        if (validDropLocations.size() > 0) { // if there is a valid drop location
            return validDropLocations.get(rand.nextInt(validDropLocations.size()));
        }
        return null;
    }
}