package jamObjects;

import assets.AssetLoader;
import boost.GameObject;
import com.mygdx.scenes.MySceneManager;

import java.util.Random;

public class Shotgun extends Skill {
    GameObject pistolR, pistolL;
    public Shotgun(Person person) {
        super(person);
        pistolR = AssetLoader.getAsset("shotgun");
        pistolR.setPosition(135, 250);
        pistolL = AssetLoader.getAsset("shotgunLeft");
        pistolL.setPosition(55, 250);
        addActor(pistolR);
    }
    float time = 1f;

    public void left() {
        removeActor(pistolR);
        removeActor(pistolL);
        addActor(pistolL);
    }

    public void right() {
        removeActor(pistolR);
        removeActor(pistolL);
        addActor(pistolR);
    }

    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (time > 1f) {
            isUsed = false;
        }
    }

    @Override
    public void use() {
        if (time < 2f) {
            return;
        } else {
            time = 0;
            float dx = person.aim.getX() - 75;
            float dy = person.aim.getY() - 150;
            float r2 = dx * dx + dy * dy;
            float r = (float) Math.sqrt(r2);
            float xx = dx / r * 1500;
            float yy = dy / r * 1500;
            Random rand = new Random();
            MySceneManager.game.addProjectile(new Projectile(person.getX() + 75, person.getY() + 250, xx - 300 + rand.nextInt(600), yy - 300 + rand.nextInt(600), person));
            MySceneManager.game.addProjectile(new Projectile(person.getX() + 75, person.getY() + 250, xx - 300 + rand.nextInt(600), yy - 300 + rand.nextInt(600), person));
            MySceneManager.game.addProjectile(new Projectile(person.getX() + 75, person.getY() + 250, xx - 300 + rand.nextInt(600), yy - 300 + rand.nextInt(600), person));
        }
    }
}
