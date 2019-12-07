package wojtek;

import boost.GameObject;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jamObjects.Person;
import jamObjects.Platform;
import stefan.PlayerGenerator;

import java.util.ArrayList;
import java.util.Random;

public class GameLoader {
    public static ArrayList<GameObject> load() {
        Random rand = new Random();

        ArrayList<GameObject> gameObjects = new ArrayList<>();



        // Platforms
        ArrayList<Platform> platforms = new ArrayList<>();
        platforms.addAll(PlatformGenerator.generate());

        // Persons
        ArrayList<GameObject> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GameObject gameObject = new Person(platforms, rand.nextInt(1000)+500, 100);
            persons.add(gameObject);
        }
        Person gameObject = new Person(platforms, 100, 100);
        gameObject.playerId = 1;
        persons.add(gameObject);
        Person gameObject2 = new Person(platforms, 1700, 100);
        gameObject2.playerId = 2;
        persons.add(gameObject2);

        gameObjects.addAll(platforms);
        gameObjects.addAll(persons);
        return gameObjects;
    }
}
