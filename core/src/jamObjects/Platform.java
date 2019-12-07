package jamObjects;

import assets.AssetLoader;
import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;

public class Platform extends GameObject {

    GameObject sprite;
    public float width;

    public Platform(float x, float y, String name) {
        super();
        setPosition(x, y);
        sprite = AssetLoader.getAnimation(name);
        ((SpriteObject) sprite).chooseAnimation("asd");
        Gdx.app.log("Plstform", "" + (((SpriteObject) sprite).animations.size()));
        addActor(sprite);
    }
}
