package jamObjects;

import boost.GameObject;
import stefan.PlayerGenerator;

public class Person extends GameObject {

    public GameObject animation;

    public Person() {
        super();
        animation = PlayerGenerator.generate();
        addActor(animation);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
