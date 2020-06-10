package game.attack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.ShootAction;
import game.ZombieCapability;

public class ShootBehaviour implements Behaviour {
	private ZombieCapability attackableTeam;
	private Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * Sets the team (i.e. ZombieCapability) that the owner of this Behaviour is
	 * allowed to attack.
	 * 
	 * @param attackableTeam Team descriptor for ZombieActors that can be attacked
	 */
	public ShootBehaviour(ZombieCapability attackableTeam) {
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
				 
				return new ShootAction(e.getDestination().getActor());
			}
		}
		return null;
	}

}
