package jamObjects;

import assets.AssetLoader;
import boost.GameObject;
import com.mygdx.gameComponents.Movement;

public class Projectile extends GameObject {
    public boolean isDestroyed;
    Movement movement;
    public Person person;
    public Projectile(float x, float y, float vx, float vy, Person person) {
        super();
        isDestroyed = false;
        this.person = person;
        addActor(AssetLoader.getAsset("shot"));
        movement = new Movement(this);
        movement.velocity.set(vx, vy);
        movement.position.set(x, y);
        setBounds(0, 0, 20, 20);
        addComponent(movement);

    }
}
