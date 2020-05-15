package game;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class DropAdjacentItemAction extends DropItemAction {

    protected Random rand = new Random();

    public DropAdjacentItemAction(Item item) {
        super(item);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<Location> validDropLocations = new ArrayList<Location>();
        Location here = map.locationOf(actor);

        actor.removeItemFromInventory(item);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                validDropLocations.add(destination);
            }
        validDropLocations.get(rand.nextInt(validDropLocations.size())).addItem(item);
        return menuDescription(actor);
    }
}