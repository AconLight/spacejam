package wojtek;

import boost.GameObject;
import jamObjects.Platform;

import java.util.ArrayList;

public class PlatformGenerator {
    public static ArrayList<GameObject> generate() {
        ArrayList<GameObject> platforms = new ArrayList<>();
        platforms.add(new Platform(200, 200, "platform1"));

        return platforms;
    }
}