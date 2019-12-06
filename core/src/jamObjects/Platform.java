package jamObjects;

import assets.AssetLoader;
import boost.GameObject;
import boost.SpriteObject;

public class Platform extends GameObject {

    GameObject sprite;

    public Platform(float x, float y, String name) {
        super();
        setPosition(x, y);
        sprite = AssetLoader.getAsset(name);
    }
}
