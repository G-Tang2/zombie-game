package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {
	protected int ammoCount;
	
	public Shotgun() {
		super("Shotgun", 's', 10, "Wacks");
		this.ammoCount = 0;
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
	

}
