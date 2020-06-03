package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;

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
            this.mapBoundaryLocation().addActor(specialActor);
        }
    }

    private Location mapBoundaryLocation() {
        int xMin = widths.min();
        int xMax = widths.max();
        int yMin = heights.min();
        int yMax = heights.max();
        int x = 0;
        int y = 0;
        boolean validLocation = false;

        while (!validLocation) {
            if (rand.nextBoolean()) {
                x = rand.nextInt((xMax - xMin) + 1) + xMin;
                if (x == xMin || x == xMax) {
                    y = rand.nextInt((yMax - yMin) + 1) + yMin;
                } else {
                    if (rand.nextBoolean()) {
                        y = yMin;
                    } else {
                        y = yMax;
                    }
                }
            } else {
                y = rand.nextInt((yMax - yMin) + 1) + yMin;
                if (y == yMin || y == yMax) {
                    x = rand.nextInt((xMax - xMin) + 1) + xMin;
                } else {
                    if (rand.nextBoolean()) {
                        x = xMin;
                    } else {
                        x = xMax;
                    }
                }
            }
            validLocation = this.at(x, y).canActorEnter(specialActor);
        }
        return at(x, y);
    }
}