package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class Sniper extends WeaponItem {
	protected int ammoCount;
	
	public Sniper() {
		super("Sniper", 'S', 10, "Thunks");
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNutrition() {
		return 0;
	}

	@Override
	public Item upgrade() {
		return null;
	}

}
