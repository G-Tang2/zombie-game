package game.item;

public class SniperAmmunition extends Ammunition {
	int ammoCount = 5;
	
	public SniperAmmunition() {
		super(".50 Cal Rounds", ';');
	}

	@Override
	public int getAmmo() {
		return this.ammoCount;
	}

}

