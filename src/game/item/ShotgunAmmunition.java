package game.item;

public class ShotgunAmmunition extends Ammunition {
	private int ammoCount = 7;

	public ShotgunAmmunition() {
		super("Shotgun Pellets", ':');

	}

	@Override
	public int getAmmo() {
		return this.ammoCount;
	}

}
