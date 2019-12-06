package stefan;

import assets.AssetLoader;
import boost.GameObject;
import boost.SpriteObject;

public class PlayerGenerator {
    public static GameObject generate() {
        int headId = 1;
        GameObject player = new GameObject();
        GameObject head = AssetLoader.getAsset("head" + headId);
        // TODO body
        SpriteObject legs = AssetLoader.getAnimation("jade");
        legs.chooseAnimation("run");

        player.addActor(legs);
        player.addActor(head);
        return player;
    }
}
