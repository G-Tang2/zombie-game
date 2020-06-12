package game.item;

public class SniperAmmunition extends Ammunition {
	private int ammoCount = 3;

	public SniperAmmunition() {
		super(".50 Cal Rounds", ';');
	}

	@Override
	public int getAmmo() {
		return this.ammoCount;
	}

}
