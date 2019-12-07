package jamObjects;

import assets.AssetLoader;
import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.gameComponents.Movement;

import java.util.Random;

public class Blood extends GameObject {
    float x, y;
    int add;
    Random rand;
    public Blood(float x, float y) {
        super();
        this.x = x;
        this.y = y;
        rand = new Random();
        add = 10;
        time = 0;
    }
    float time;
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (add > 0) {
            add();
            add --;
        }
        for (Actor s: getChildren()) {
            Gdx.app.log("blood", "blood");
            SpriteObject so = ((SpriteObject) s);
            so.setScale(3 - time/2);
            if (time >= 6) {
                remove();
            }
        }
    }

    void add() {
        SpriteObject b = AssetLoader.getAsset("blood");
        b.setPosition(x + rand.nextInt(100)-50 + 75, y + rand.nextInt(100)-50 + 250);
        Movement movement = new Movement(b);
        movement.acceleration.set(0, -500);
        movement.velocity.set(rand.nextInt(300)-150, rand.nextInt(300)-50);
        b.addComponent(movement);
        addActor(b);
    }
}
