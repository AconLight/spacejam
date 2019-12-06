package wojtek;

import boost.GameObject;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jamObjects.Person;
import stefan.PlayerGenerator;

import java.util.ArrayList;
import java.util.Random;

public class GameLoader {
    public static ArrayList<GameObject> load() {
        Random rand = new Random();

        ArrayList<GameObject> gameObjects = new ArrayList<>();

        // Persons
        ArrayList<GameObject> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            GameObject gameObject = new Person();
            gameObject.setPosition(rand.nextInt(1800), 10);
            persons.add(gameObject);
        }

        // Platforms
        ArrayList<GameObject> platforms = new ArrayList<>();
        platforms.addAll(PlatformGenerator.generate());

        gameObjects.addAll(platforms);
        gameObjects.addAll(persons);
        return gameObjects;
    }
}
