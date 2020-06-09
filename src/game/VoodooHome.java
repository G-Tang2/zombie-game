package game;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;
import edu.monash.fit2099.engine.Location;
import game.actor.VoodooPriestess;

public class VoodooHome extends GameMap {

    VoodooPriestess voodooPriestess;
    GameMap gameMap;
    Random rand = new Random();

    public VoodooHome(GroundFactory groundFactory, List<String> lines, VoodooPriestess voodooPriestess,
            GameMap gameMap) {
        super(groundFactory, lines);
        this.voodooPriestess = voodooPriestess;
        this.gameMap = gameMap;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.contains(voodooPriestess) && rand.nextInt(100) < 5) {
            gameMap.moveActor(voodooPriestess, mapBoundaryLocation());
        }
    }

    private Location mapBoundaryLocation() {
        int xMin = gameMap.getXRange().min();
        int xMax = gameMap.getXRange().max();
        int yMin = gameMap.getYRange().min();
        int yMax = gameMap.getYRange().max();
        int x = 0;
        int y = 0;
        boolean validLocation = false;

        while (!validLocation) {
            // random x co-ordinate generated first then y co-ordinate
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
            }
            // rand y co-ordinate generated first then x co-ordinate
            else {
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
            validLocation = gameMap.at(x, y).canActorEnter(voodooPriestess);
        }
        return gameMap.at(x, y);
    }
}
