package jamObjects;

import assets.AssetLoader;
import boost.SceneManager;
import com.badlogic.gdx.Gdx;
import com.mygdx.scenes.MySceneManager;

public class Pistol extends Skill {
    public Pistol(Person person) {
        super(person);
        addActor(AssetLoader.getAsset("pistol"));
    }
    float time = 1f;

    public void act(float delta) {
        super.act(delta);
        time += delta;
    }

    @Override
    public void use() {
        if (time < 1f) {
            return;
        } else {
            time = 0;
            float dx = person.aim.getX() - 75;
            float dy = person.aim.getY() - 150;
            float r2 = dx * dx + dy * dy;
            float r = (float) Math.sqrt(r2);
            float xx = dx / r * 3000;
            float yy = dy / r * 3000;
            MySceneManager.game.addProjectile(new Projectile(person.getX() + 75, person.getY() + 150, xx, yy));
        }
    }
}
