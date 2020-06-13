package game.item;

/**
 * Shotgun ammunition.
 * 
 * @author Mike Kevin Balapitiya
 * 
 */
public class ShotgunAmmunition extends Ammunition {
	private int ammoCount = 7;

	/**
	 * Constructor.
	 * 
	 */
	public ShotgunAmmunition() {
		super("Shotgun Pellets", ':');

	}

	@Override
	public int getAmmo() {
		return this.ammoCount;
	}

}
