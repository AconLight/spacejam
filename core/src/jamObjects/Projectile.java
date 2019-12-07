package jamObjects;

import assets.AssetLoader;
import boost.GameObject;
import com.mygdx.gameComponents.Movement;

public class Projectile extends GameObject {
    Movement movement;
    public Projectile(float x, float y, float vx, float vy) {
        super();
        addActor(AssetLoader.getAsset("shot"));
        movement = new Movement(this);
        movement.velocity.set(vx, vy);
        movement.position.set(x, y);
        setBounds(0, 0, 20, 20);
        addComponent(movement);

    }
}
