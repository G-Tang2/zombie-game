package game.item;

import edu.monash.fit2099.engine.Item;

public class ZombieArm extends ZombieLimb {

    public ZombieArm(String name, char displayChar, int damage, String verb) {
        super(name, displayChar, damage, verb);
    }

    @Override
    public Item upgrade() {
        return new ZombieClub();
    }

}