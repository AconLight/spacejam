package wojtek;

import boost.GameObject;
import jamObjects.Platform;

import java.util.ArrayList;

public class PlatformGenerator {
    public static ArrayList<GameObject> generate() {
        ArrayList<GameObject> platforms = new ArrayList<>();

        //podloga
        for(int x = 0; x <= 1920; x+=200)
            platforms.add(new Platform(x, 0, "platform1"));

        platforms.add(new Platform(800, 40, "platform2"));
        platforms.add(new Platform(1830, 40, "platform2"));

        platforms.add(new Platform(200, 270, "platform1"));
        platforms.add(new Platform(400, 270, "platform1"));
        platforms.add(new Platform(350, 300, "platform2"));
        platforms.add(new Platform(1500, 270, "platform1"));

        platforms.add(new Platform(1000, 310, "platform1"));

        platforms.add(new Platform(0, 540, "platform1"));
        platforms.add(new Platform(600, 600, "platform1"));
        platforms.add(new Platform(1250, 410, "platform2"));

        platforms.add(new Platform(1020, 600, "platform2"));



        return platforms;
    }
}
