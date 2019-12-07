package jamObjects;

import assets.AssetLoader;
import boost.GameObject;
import com.mygdx.scenes.MySceneManager;

public class PistolShield extends Skill {
    int lives;
    GameObject pistolR, pistolL, shield;
    public void shield() {
        removeActor(shield);
    }
    public PistolShield(Person person) {
        super(person);
        lives = 1;
        pistolR = AssetLoader.getAsset("pistol");
        pistolR.setPosition(135, 250);
        pistolL = AssetLoader.getAsset("pistolLeft");
        pistolL.setPosition(55, 250);
        addActor(pistolR);
        shield = AssetLoader.getAsset("shield");
        shield.setPosition(70, 130);
        addActor(shield);
        AssetLoader.tarcza.play();
    }
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
            MySceneManager.game.addProjectile(new Projectile(person.getX() + 75, person.getY() + 250, xx, yy, person));
        }
    }
}
