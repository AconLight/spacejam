package stefan;

import assets.AssetLoader;
import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public class PlayerGenerator {

    static int  nHeads = 1,
                nBodys = 1,
                nLegs = 1;

    public static GameObject generate() {
        GameObject player = new GameObject();
        Random random = new Random();

        SpriteObject head = AssetLoader.getAnimation("head");
        head.chooseAnimation("head" + random.nextInt(nHeads));
        SpriteObject body = AssetLoader.getAnimation("body");
        body.chooseAnimation("body"  + random.nextInt(nBodys));
        SpriteObject legs = AssetLoader.getAnimation("legs");
        legs.chooseAnimation("legs" + random.nextInt(nLegs));
        legs.currentAnimation.setFrameDuration(0.125f);
        legs.color = new Color(0.4f, 0.5f, 0.7f, 1f);


        player.addActor(legs);
        player.addActor(body);
        player.addActor(head);
        return player;
    }

    

}
