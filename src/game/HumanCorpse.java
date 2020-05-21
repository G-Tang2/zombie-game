package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

public class HumanCorpse extends PortableItem {
    private Random rand = new Random();
    private int deathTime;
    private int MINDEATHTIME = 5;
    private int MAXDEATHTIME = 10;

    public HumanCorpse(String name, char displayChar) {
        super(name, displayChar);
        deathTime = rand.nextInt((MAXDEATHTIME - MINDEATHTIME) + 1) + MINDEATHTIME;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        deathTime--;
        if (deathTime < 0) {
            location.removeItem(this);
            Zombie zombie = new Zombie(name);
            if (location.containsAnActor()) {
                randAdjacentLocation(zombie, location).addActor(zombie);
            } else {
                location.addActor(zombie);
            }
        }
    }

    @Override
    public void tick(Location location, Actor actor) {
        super.tick(location);
        deathTime--;
        if (deathTime < 0) {
            Zombie zombie = new Zombie(name);
            Location spawnLocation = randAdjacentLocation(zombie, location);
            if (spawnLocation != null) {
                spawnLocation.addActor(zombie);
                actor.removeItemFromInventory(this);
            }
        }
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
        } else {
            return null;
        }
    }

}