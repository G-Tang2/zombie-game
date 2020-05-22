package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

/**
 * Human Corpses for when a human dies but has not become a zombie yet
 * 
 * @author Garvin Tang
 * 
 */
public class HumanCorpse extends PortableItem {
    private Random rand = new Random();
    private int deathTime;
    private int MINDEATHTIME = 5;
    private int MAXDEATHTIME = 10;

    /**
     * Constructor.
     *
     * @see PortableItem#HumanCorpse(String, char)
     * @param name the name of the corpse
     * @param displayChar the character to be displayed on the map for item
     */
    public HumanCorpse(String name, char displayChar) {
        super(name, displayChar);
        deathTime = rand.nextInt((MAXDEATHTIME - MINDEATHTIME) + 1) + MINDEATHTIME;
    }

    /**
	 * Corpses experience time passing and may become a Zombie
	 * This tick method is for if the corpse is on the ground
	 *
	 * @see Item#tick(Location)
	 * @param location The location where the corpse is.
	 */
    @Override
    public void tick(Location location) {
        super.tick(location);
        deathTime--;
        if (deathTime <= 0) {
            location.removeItem(this);
            Zombie zombie = null;
            try {
                zombie = new Zombie(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (location.containsAnActor()) {
                randAdjacentLocation(zombie, location).addActor(zombie);
            } else {
                location.addActor(zombie);
            }
        }
    }

    /**
	 * Corpses experience time passing and may become a Zombie
	 * This tick method is for if the corpse is in the players inventory
	 *
	 * @see Item#tick(Location, Actor)
	 * @param location The location where the corpse is.
	 * @param actor the actor carrying the corpse
	 */
    @Override
    public void tick(Location location, Actor actor) {
        super.tick(location);
        deathTime--;
        if (deathTime < 0) {
            Zombie zombie = null;
            try {
                zombie = new Zombie(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
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