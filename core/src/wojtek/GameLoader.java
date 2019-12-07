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
            GameObject gameObject = new Person(platforms);
            gameObject.setPosition(1920/2, 10);
            persons.add(gameObject);
        }

        gameObjects.addAll(platforms);
        gameObjects.addAll(persons);
        return gameObjects;
    }
}
