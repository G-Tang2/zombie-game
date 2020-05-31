package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class Car extends Item{

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
}

