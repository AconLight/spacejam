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

    public static GameObject generate(float r, float g, float b) {
            GameObject player = new GameObject();
            Random random = new Random();

            SpriteObject head = AssetLoader.getAnimation("head");
            head.chooseAnimation("head" + random.nextInt(nHeads) + "_right");
            SpriteObject body = AssetLoader.getAnimation("body");
            body.color = new Color(g, b, r, 1f);
            body.chooseAnimation("body"  + random.nextInt(nBodys) + "_right");
            SpriteObject legs = AssetLoader.getAnimation("legs");
            legs.chooseAnimation("legs" + random.nextInt(nLegs) + "_right");
            // legs.currentAnimation.setFrameDuration(0.125f);
            legs.color = new Color(r, g, b, 1f);


        player.addActor(legs);
        player.addActor(body);
        player.addActor(head);
        return player;
    }

    public static GameObject generateLeft(float r, float g, float b) {
        GameObject player = new GameObject();

        SpriteObject head = AssetLoader.getAnimation("head");
        head.chooseAnimation("head0" + /*random.nextInt(nHeads) +*/ "_left");
        SpriteObject body = AssetLoader.getAnimation("body");
        body.color = new Color(g, b, r, 1f);
        body.chooseAnimation("body0"  + /*random.nextInt(nBodys) +*/ "_left");
        SpriteObject legs = AssetLoader.getAnimation("legs");
        legs.chooseAnimation("legs0" + /*random.nextInt(nLegs) +*/ "_left");
        // legs.currentAnimation.setFrameDuration(0.125f);
        legs.color = new Color(r, g, b, 1f);

        player.addActor(legs);
        player.addActor(body);
        player.addActor(head);
        return player;
    }

    public static GameObject generateRight(float r, float g, float b) {
        GameObject player = new GameObject();

        SpriteObject head = AssetLoader.getAnimation("head");
        head.chooseAnimation("head0_right");
        SpriteObject body = AssetLoader.getAnimation("body");
        body.color = new Color(g, b, r, 1f);
        body.chooseAnimation("body0_right");
        SpriteObject legs = AssetLoader.getAnimation("legs");
        legs.chooseAnimation("legs0" + /*random.nextInt(nLegs) +*/ "_right");
        // legs.currentAnimation.setFrameDuration(0.125f);
        legs.color = new Color(r, g, b, 1f);


        player.addActor(legs);
        player.addActor(body);
        player.addActor(head);
        return player;
    }

    public static GameObject generateJumpLeft(float r, float g, float b){

        GameObject player = new GameObject();

        SpriteObject head = AssetLoader.getAnimation("head");
        head.chooseAnimation("head0" + /*random.nextInt(nHeads) +*/ "_left");
        SpriteObject body = AssetLoader.getAnimation("body");
        body.color = new Color(g, b, r, 1f);
        body.chooseAnimation("body0"  + /*random.nextInt(nBodys) +*/ "_left");
        SpriteObject legs = AssetLoader.getAnimation("legs");
        legs.chooseAnimation("jump");
        // legs.currentAnimation.setFrameDuration(0.125f);
        legs.color = new Color(r, g, b, 1f);

        player.addActor(legs);
        player.addActor(body);
        player.addActor(head);
        return player;
    }

    public static GameObject generateJumpRight(float r, float g, float b){
        GameObject player = new GameObject();

        SpriteObject head = AssetLoader.getAnimation("head");
        head.chooseAnimation("head0_right");
        SpriteObject body = AssetLoader.getAnimation("body");
        body.color = new Color(g, b, r, 1f);
        body.chooseAnimation("body0_right");
        SpriteObject legs = AssetLoader.getAnimation("legs");
        legs.chooseAnimation("jump");
        // legs.currentAnimation.setFrameDuration(0.125f);
        legs.color = new Color(r, g, b, 1f);

        player.addActor(legs);
        player.addActor(body);
        player.addActor(head);
        return player;
    }


}
