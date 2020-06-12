package game.item;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class Car extends Item {

	public Car() {
		super("Car", '=', false);
	}

	public void addAction(Action action) {
		this.allowableActions.add(action);
	}

	@Override
	public int getNutrition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item upgrade() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAmmo(int ammo) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getAmmo() {
		// TODO Auto-generated method stub
		return 0;
	}
}
