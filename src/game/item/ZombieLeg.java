package game.item;

import edu.monash.fit2099.engine.Item;

public class ZombieLeg extends ZombieLimb {

    public ZombieLeg(String name, char displayChar, int damage, String verb) {
        super(name, displayChar, damage, verb);
    }

    @Override
    public Item upgrade() {
        return new ZombieMace();
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