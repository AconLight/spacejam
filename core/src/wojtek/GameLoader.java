package wojtek;

import boost.GameObject;
import com.badlogic.gdx.scenes.scene2d.Stage;
import jamObjects.Person;
import jamObjects.Pistol;
import jamObjects.Platform;
import stefan.PlayerGenerator;

import java.util.ArrayList;
import java.util.Random;

public class GameLoader {

    public static ArrayList<Platform> platforms;

    public static ArrayList<GameObject> load() {
        Random rand = new Random();

        ArrayList<GameObject> gameObjects = new ArrayList<>();



        // Platforms
        platforms = new ArrayList<>();
        platforms.addAll(PlatformGenerator.generate());

        // Persons
        ArrayList<GameObject> persons = new ArrayList<>();

        persons.add(new Person(platforms, 300, 300, 0));
        persons.add(new Person(platforms, 700, 600, 0));
        persons.add(new Person(platforms, 1600, 270, 0));
        persons.add(new Person(platforms, 1050, 310, 0));

        persons.add(createPerson());
        persons.add(createPerson());
        persons.add(createPerson());

        Person gameObject = new Person(platforms, 100, 100, 0);
        gameObject.setSkill(new Pistol(gameObject));
        gameObject.playerId = 1;
        gameObject.setAsPlayer();
        persons.add(gameObject);
        Person gameObject2 = new Person(platforms, 1700, 100, 0);
        gameObject2.setSkill(new Pistol(gameObject2));
        gameObject2.playerId = 2;
        gameObject2.setAsPlayer();
        persons.add(gameObject2);

        gameObjects.addAll(platforms);
        gameObjects.addAll(persons);
        return gameObjects;
    }

    public static GameObject createPerson(){
        Random random = new Random();

        if(random.nextBoolean())
            return new Person(platforms, 1750, 950, -1);
        else
            return new Person(platforms, -250, 900, 1);
    }
}
