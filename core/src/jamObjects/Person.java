package jamObjects;

import boost.GameObject;
import com.badlogic.gdx.Gdx;
import stefan.PlayerGenerator;

public class Person extends GameObject {

    public GameObject animation;

    public Person() {
        super();
        animation = PlayerGenerator.generate();
        animation.setScale(4);
        addActor(animation);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
