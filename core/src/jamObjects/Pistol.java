package jamObjects;

import assets.AssetLoader;
import boost.SceneManager;
import com.mygdx.scenes.MySceneManager;

public class Pistol extends Skill {
    public Pistol(Person person) {
        super(person);
        addActor(AssetLoader.getAsset("pistol"));
    }

    @Override
    public void use() {
        MySceneManager.game.stage.addActor(new Projectile(person.getX() + 50, person.getY() + 150, person.movement.velocity.x, person.movement.velocity.y));
    }
}
