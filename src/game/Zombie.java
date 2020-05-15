package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;

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
	private int armCount;
	private int legCount;

	private Behaviour[] behaviours = { new ScavengeBehaviour(), new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10), new WanderBehaviour() };

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
		armCount = 2;
		legCount = 2;
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}

	public IntrinsicWeapon getBitingWeapon() {
		return new IntrinsicWeapon(20, "bites");
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
		if (rand.nextInt(100) < 10) {
			display.println(name + ": Braaaaains");
		}
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	void loseArm() {
		if (armCount < 0) {
			armCount--;
		}
	}

	void loseLeg() {
		if (legCount < 0) {
			legCount--;
		}
	}

	int getArmCount() {
		return armCount;
	}

	int getLegCount() {
		return legCount;
	}
}
