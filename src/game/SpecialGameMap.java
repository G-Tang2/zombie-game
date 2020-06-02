package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

public class SpecialGameMap extends GameMap {

    private Actor specialActor;
    private Random rand = new Random();

    public SpecialGameMap(GroundFactory groundFactory, List<String> lines, Actor actor) {
        super(groundFactory, lines);
        specialActor = actor;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.contains(specialActor) && rand.nextInt(100) < 5) {
            this.at(0, 0).addActor(specialActor);
        }
    }
}