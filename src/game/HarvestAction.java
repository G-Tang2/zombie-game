package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class HarvestAction extends Action {

    private Location location;
    private Random rand = new Random();

    public HarvestAction(Location location) {
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Farmer) {
            ArrayList<Location> validDropLocations = new ArrayList<Location>();

            for (Exit exit : location.getExits()) {
                Location destination = exit.getDestination();
                if (destination.getGround().canActorEnter(actor)) {
                    validDropLocations.add(destination);
                }
            }
            validDropLocations.get(rand.nextInt(validDropLocations.size())).addItem(new Food());
        } else if (actor instanceof Player) {
            actor.addItemToInventory(new Food());
        }
        location.setGround(new Dirt());
        return actor + " harvested the ripe crop";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Harvest the ripe crop";
    }

}
