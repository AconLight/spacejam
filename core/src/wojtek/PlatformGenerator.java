package wojtek;

import boost.GameObject;
import jamObjects.Platform;

import java.util.ArrayList;

public class PlatformGenerator {

    public static ArrayList<Platform> generate() {
        ArrayList<Platform> platforms = new ArrayList<>();

        //podloga
        for(int x = -100; x <= 1920; x+=200)
            platforms.add(new Platform(x, 0, "platform1"));

        for(int x = 200; x <= 1920; x+=500)
            platforms.add(new Platform(x, 0, "small"));

        platforms.add(new Platform(200, 270, "platform1"));
        platforms.add(new Platform(400, 270, "platform1"));

        platforms.add(new Platform(1500, 270, "platform1"));

        platforms.add(new Platform(1000, 310, "platform1"));

        platforms.add(new Platform(0, 540, "platform1"));
        platforms.add(new Platform(800, 600, "small"));
        platforms.add(new Platform(600, 600, "platform1"));

        platforms.add(new Platform(1200, 700, "platform1"));
        platforms.add(new Platform(1400, 700, "platform1"));

        platforms.add(new Platform(1680, 900, "platform1"));
        platforms.add(new Platform(1800, 800, "platform1"));
        platforms.add(new Platform(-180, 800, "platform1"));
        platforms.add(new Platform(-280, 800, "platform1"));

        return platforms;
    }
}
