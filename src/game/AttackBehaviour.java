package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class that generates an AttackAction if the current Actor is standing next
 * to an Actor that they can attack.
 * 
 * @author ram
 * @author Garvin Tang
 *
 */
public class AttackBehaviour implements Behaviour {

	private ZombieCapability attackableTeam;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this Behaviour is
	 * allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public AttackBehaviour(ZombieCapability attackableTeam) {
		this.attackableTeam = attackableTeam;
	}

	/**
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * 
	 * Actors are attackable if their ZombieCapability matches the "undeadness
	 * status" set
	 * 
	 * @see Behaviour#getAction(Actor, GameMap)
	 * @param actor The Actor acting.
	 * @param map   The map the actor is on.
	 * @return an Action that actor can perform, or null if actor can't do this.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(actor).getExits());
		Collections.shuffle(exits);

		// searches adjacent passable locations for an actor of opposite team to attack
		for (Exit e : exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				if (actor.hasCapability(ZombieCapability.UNDEAD)) {
					int biteProbability = 50; // default bite probability of 50%
					if (actor.getArmCount() == 1) {
						biteProbability *= 1.5;
					} else if (actor.getArmCount() == 0) {
						biteProbability = 100;
					}
					if (rand.nextInt(100) < biteProbability) {
						return new BiteAction(e.getDestination().getActor());
					}
				}
				return new AttackAction(e.getDestination().getActor());
			}
		}
		return null;
	}

}
