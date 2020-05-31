package game.zombie;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import game.Behaviour;
import game.human.Human;
import game.speech.SpeechBehaviour;
import game.HuntBehaviour;
import game.ScavengeBehaviour;
import game.WanderBehaviour;
import game.ZombieActor;
import game.ZombieCapability;
import game.attack.AttackBehaviour;
import game.drop.DropAdjacentItemAction;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring. It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {

	private Random rand = new Random();
	private boolean movedLastTurn = false;

	private Behaviour[] behaviours = { new ScavengeBehaviour(), new SpeechBehaviour(),
			new AttackBehaviour(ZombieCapability.ALIVE) };

	private Behaviour[] moveBehaviours = { new HuntBehaviour(Human.class, 10), new WanderBehaviour() };

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}

	/**
	 * If a Zombie can attack, it will. If not, it will chase any human within 10
	 * spaces. If no humans are close enough it will wander randomly.
	 * 
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map        the map where the current Zombie is
	 * @param display    the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) {
				movedLastTurn = false;
				return action;
			}
		}
		if (canMove()) {
			for (Behaviour behaviour : moveBehaviours) {
				Action action = behaviour.getAction(this, map);
				if (action != null) {
					movedLastTurn = true;
					return action;
				}
			}
		}
		movedLastTurn = false;
		return new DoNothingAction();
	}

	/**
	 * The zombie will receive damage and possibly lose some limbs
	 * 
	 * @see ZombieActor#hurt(int)
	 * @param points the amount of damage inflicted on the zombie
	 */
	@Override
	public void hurt(int points) {
		hitPoints -= points;
		if (legCount + armCount > 0 && rand.nextInt(100) < 25) {
			dropLimbs();
		}
	}

	private void dropLimbs() {
		int limbsLost = 0;
		int val = rand.nextInt(100);
		if (val > 15) {
			limbsLost = 1;
		} else if (val > 5) {
			limbsLost = 2;
		} else if (val > 1) {
			limbsLost = 3;
		} else {
			limbsLost = 4;
		}
		while (legCount + armCount > 0 && limbsLost > 0) {
			if (legCount <= 0 || (armCount > 0 && rand.nextBoolean())) {
				this.actions.add(new DropAdjacentItemAction(new ZombieArm("Zombie arm", '~', 10, "slaps")));
				armCount--;
				if (armCount == 0 || (armCount == 1 && rand.nextBoolean())) {
					dropWeapon();
				}
			} else {
				this.actions.add(new DropAdjacentItemAction(new ZombieLeg("Zombie leg", '/', 12, "slaps")));
				legCount--;
			}
			limbsLost--;
		}
	}

	private void dropWeapon() {
		for (Item item : getInventory()) {
			if (item.asWeapon() != null) {
				this.actions.add(new DropAdjacentItemAction(item));
				return;
			}
		}
	}

	private boolean canMove() {
		if (legCount < 2) {
			if (legCount == 0 || (legCount == 1 && movedLastTurn)) {
				return false;
			}
		}
		return true;
	}
}
