package game;

import edu.monash.fit2099.engine.Item;

public class Food extends Item {
	
	private int nutrition = 20;
	
	public Food() {
		super("food", 'f', true);
	}
	
	public void eat() {
		return nutrition;
	}

}
