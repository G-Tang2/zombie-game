package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;
import game.actor.Farmer;
import game.actor.Human;
import game.actor.Player;
import game.actor.VoodooPriestess;
import game.actor.Zombie;
import game.ground.Dirt;
import game.ground.Fence;
import game.ground.Tree;
import game.ground.Wall;
import game.item.Car;
import game.item.Plank;
import game.item.Shotgun;
import game.item.Sniper;

/**
 * The main class for the zombie apocalypse game.
 *
 * @author ram
 * @author Garvin Tang
 * @author Mike Kevin Balapitiya
 * 
 */
public class Application {

	public static void main(String[] args) {
		World world = new MultiMapWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Fence(), new Tree(), new Wall());

		List<String> map = Arrays.asList(
				"................................................................................",
				"................................................................................",
				"....................................##########..................................",
				"..........................###########........#####..............................",
				"............++...........##......................########.......................",
				"..............++++.......#..............................##......................",
				".............+++...+++...#...............................#......................",
				".........................##..............................##.....................",
				"..........................#...............................#.....................",
				".........................##...............................##....................",
				".........................#...............................##.....................",
				".........................###..............................##....................",
				"...........................####......................######.....................",
				"..............................#########.........####............................",
				"............+++.......................#.........#...............................",
				".............+++++....................#.........#...............................",
				"...............++........................................+++++..................",
				".............+++....................................++++++++....................",
				"............+++.......................................+++.......................",
				"................................................................................",
				".........................................................................++.....",
				"........................................................................++.++...",
				".........................................................................++++...",
				"..........................................................................++....",
				"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		List<String> townMap = Arrays.asList(
				".................................|||||||||......................................",
				".................................|.......|......................................",
				".................................|.......|......................................",
				".................................|.......|......................................",
				".................................||||.||||......................................",
				"................................................................................",
				"................................................................................",
				"....................||||||||....................||||||||........................",
				"....................|......|....................|......|........................",
				"....................|..................................|........................",
				"....................|......|....................|......|........................",
				"....................||||||||....................||||||||........................",
				"................................................................|||||||||||.....",
				"................................................................|.........|.....",
				"................................................................|.........|.....",
				"....................||||||||....................||||||||........|...............",
				"....................|......|....................|......|........|...............",
				"....................|..................................|........|.........|.....",
				"....................|......|....................|......|........|.........|.....",
				"....................||||||||....................||||||||........|||||||||||.....",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................",
				"................................................................................");
		GameMap town = new GameMap(groundFactory, townMap);
		world.addGameMap(town);

		// Mambo Marie
		VoodooPriestess mamboMarie = new VoodooPriestess("Mambo Marie");
		List<String> voodooMap = Arrays.asList(".");
		VoodooHome voodooHome = new VoodooHome(groundFactory, voodooMap, mamboMarie, gameMap);
		world.addGameMap(voodooHome);

		voodooHome.addActor(mamboMarie, voodooHome.at(0, 0));
		mamboMarie.setHome(voodooHome);

		Car factoryCar = new Car();
		factoryCar.addAction(new MoveActorAction(town.at(42, 21), "to the town"));
		gameMap.at(42, 22).addItem(factoryCar);

		Car townCar = new Car();
		townCar.addAction(new MoveActorAction(gameMap.at(42, 21), "to the factory"));
		town.at(42, 22).addItem(townCar);

		Actor player = new Player("Player", '@', 100);

		world.addPlayer(player, gameMap.at(42, 15));

		// Place some random humans
		String[] humans = { "Carlton", "May", "Vicente", "Andrea", "Wendy", "Elina", "Winter", "Clem", "Jacob",
				"Jaquelyn" };
		int x, y;
		for (String name : humans) {
			do {
				x = (int) Math.floor(Math.random() * 20.0 + 30.0);
				y = (int) Math.floor(Math.random() * 7.0 + 5.0);
			} while (gameMap.at(x, y).containsAnActor());
			gameMap.at(x, y).addActor(new Human(name));
		}
		// place a simple weapon
		gameMap.at(74, 20).addItem(new Plank());

		// zombies
		gameMap.at(30, 20).addActor(new Zombie("Groan"));
		gameMap.at(30, 18).addActor(new Zombie("Boo"));
		gameMap.at(10, 4).addActor(new Zombie("Uuuurgh"));
		gameMap.at(50, 18).addActor(new Zombie("Mortalis"));
		gameMap.at(1, 10).addActor(new Zombie("Gaaaah"));
		gameMap.at(62, 12).addActor(new Zombie("Aaargh"));

		// farmers
		gameMap.at(40, 9).addActor(new Farmer("Bob"));
		
		// Humans on town
		town.at(23, 8).addActor(new Human("Jason"));
		town.at(23, 18).addActor(new Human("Tommy"));
		town.at(36, 3).addActor(new Human("Arjun"));
		town.at(53, 9).addActor(new Human("Raquib"));
		town.at(53, 16).addActor(new Human("Roshane"));
		town.at(69, 17).addActor(new Human("Rushan"));
		
		// Zombies on town
		town.at(10, 4).addActor(new Zombie("Sajith"));
		town.at(5, 14).addActor(new Zombie("Sayan"));
		town.at(60, 3).addActor(new Zombie("Atheish"));
		town.at(60, 23).addActor(new Zombie("Ras"));
		town.at(14, 19).addActor(new Zombie("Kartik"));
		
		// Place sniper and shotgun
		//town.at(71, 17).addItem(new Sniper());
		town.at(42, 19).addItem(new Sniper());
		//town.at(26, 10).addItem(new Shotgun());
		town.at(42, 20).addItem(new Shotgun());
		
		town.at(41, 20).addItem(new ShotgunAmmunation());

		world.run();
	}
}
