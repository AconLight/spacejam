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
        sprite = AssetLoader.getAsset(name);
        width = sprite.getWidth();
        Gdx.app.log("Platform", sprite.getWidth() + ", " + sprite.getHeight());
        addActor(sprite);
    }
}
