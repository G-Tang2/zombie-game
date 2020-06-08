package game;

import java.util.Iterator;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.World;

public class MultiMapWorld extends World {

    public MultiMapWorld(Display display) {
        super(display);
    }

    @Override
    protected void processActorTurn(Actor actor) {
        Location here = actorLocations.locationOf(actor);
        GameMap map = here.map();

        Actions actions = new Actions();
        for (Item item : actor.getInventory()) {
            actions.add(item.getAllowableActions());
            // Game rule. If you're carrying it, you can drop it.
            actions.add(item.getDropAction());
        }

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();

            // Game rule. You don't get to interact with the ground if someone is standing
            // on it.
            if (actorLocations.isAnActorAt(destination)) {
                // adds actions actor at destination can do to current actor
                actions.add(actorLocations.getActorAt(destination).getAllowableActions(actor, exit.getName(), map));
            } else {
                // adds actions current actor can do to destination
                actions.add(destination.getGround().allowableActions(actor, destination, exit.getName()));
            }
            // adds move action to destination if actor can enter
            actions.add(destination.getMoveAction(actor, exit.getName(), exit.getHotKey()));
        }

        for (Item item : here.getItems()) {
            actions.add(item.getAllowableActions());
            // Game rule. If it's on the ground you can pick it up.
            actions.add(item.getPickUpAction());
        }
        actions.add(new DoNothingAction());

        Action action = actor.playTurn(actions, lastActionMap.get(actor), map, display);
        lastActionMap.put(actor, action);

        String result = action.execute(actor, map);
        if (map.contains(player)) {
            display.println(result);
        }
    }

    @Override
    protected boolean stillRunning() {
        if (lastActionMap.get(player) instanceof Quit || !actorLocations.contains(player) || allUndeadDead()) {
            return false;
        }
        return true;
    }

    private boolean allUndeadDead() {
        Iterator<Actor> itr = actorLocations.iterator();
        while (itr.hasNext()) {
            Actor actor = itr.next();
            if (actor.hasCapability(ZombieCapability.UNDEAD)) {
                return false;
            }
        }
        return true;
    }

    public void addSpecialActor(VoodooPriestess actor) {
        this.addPlayer(actor, null);
    }
}