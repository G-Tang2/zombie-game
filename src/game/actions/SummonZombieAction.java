package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.SpawnActor;

public class SummonZombieAction extends Action {
    String[] names = { "Aberycusgentylis", "Ah", "Alphabeta", "Arse", "Burp", "Balls", "Banana", "Bony M", "Drug",
            "Earwacker", "Flappy", "Gassy", "Hairy", "One Too Many", "Sucker", "Poof" };
    private int numOfZombies;
    private Random rand = new Random();

    public SummonZombieAction(int numOfZombies) {
        this.numOfZombies = numOfZombies;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        for (int i = 0; i < numOfZombies; i++) {
            Boolean spawnSuccessful = false;
            while (!spawnSuccessful) {
                int x = rand.nextInt((map.getXRange().max() - map.getXRange().min()) + 1) + map.getXRange().min();
                int y = rand.nextInt((map.getYRange().max() - map.getYRange().min()) + 1) + map.getYRange().min();
                spawnSuccessful = new SpawnActor().spawn(this.names[rand.nextInt(this.names.length)], map.at(x, y));
            }
        }
        return actor + " summoned " + this.numOfZombies + " zombies";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " summon zombies";
    }
}
