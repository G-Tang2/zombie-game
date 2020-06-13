package game.action;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

class SummonZombieAction extends Action { // package-private
    private String[] names = { "Aberycusgentylis", "Ah", "Alphabeta", "Arse", "Burp", "Balls", "Banana", "Bony M",
            "Drug", "Earwacker", "Flappy", "Gassy", "Hairy", "One Too Many", "Sucker", "Poof" };
    private int numOfZombies;
    private Random rand = new Random();

    SummonZombieAction(int numOfZombies) { // package-private
        this.numOfZombies = numOfZombies;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        SpawnActor action = new SpawnActor();
        for (int i = 0; i < numOfZombies; i++) {
            Boolean spawnSuccessful = false;
            while (!spawnSuccessful) {
                int x = rand.nextInt((map.getXRange().max() - map.getXRange().min()) + 1) + map.getXRange().min(); // random
                                                                                                                   // x
                                                                                                                   // co-ordinate
                int y = rand.nextInt((map.getYRange().max() - map.getYRange().min()) + 1) + map.getYRange().min(); // random
                                                                                                                   // y
                                                                                                                   // co-ordinate
                spawnSuccessful = action.spawn(this.names[rand.nextInt(this.names.length)], map.at(x, y));
            }
        }
        return actor + " summoned " + this.numOfZombies + " zombies";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " summon zombies";
    }
}
