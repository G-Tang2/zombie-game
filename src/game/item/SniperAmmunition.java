package game.item;

/**
 * Sniper ammunition.
 * 
 * @author Mike Kevin Balapitiya
 * 
 */
public class SniperAmmunition extends Ammunition {
	private int ammoCount = 3;

	/**
	 * Constructor.
	 */
	public SniperAmmunition() {
		super(".50 Cal Rounds", ';');
	}

	@Override
	public int getAmmo() {
		return this.ammoCount;
	}

}
