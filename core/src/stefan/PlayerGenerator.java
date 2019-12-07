package stefan;

import assets.AssetLoader;
import boost.GameObject;
import boost.SpriteObject;

import java.util.Random;

public class PlayerGenerator {

    static int  nHeads = 1,
                nBodys = 1,
                nLegs = 1;

    public static GameObject generate() {
        GameObject player = new GameObject();
        Random random = new Random();

        SpriteObject head = AssetLoader.getAnimation("head" + random.nextInt(nHeads));
        SpriteObject body = AssetLoader.getAnimation("body" + random.nextInt(nBodys));
        SpriteObject legs = AssetLoader.getAnimation("legs" + random.nextInt(nLegs));

        player.addActor(legs);
        player.addActor(body);
        player.addActor(head);
        return player;
    }



}
