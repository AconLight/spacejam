package jamObjects;

import boost.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.gameComponents.Movement;
import com.mygdx.gameComponents.MovementDrag;
import stefan.PlayerGenerator;

import java.util.ArrayList;

public class Person extends GameObject {

    public int playerId;
    public boolean isStanding;
    public GameObject animation;
    public Movement movement;
    public MovementDrag movementDrag;

    ArrayList<Platform> platforms;

    public Person(ArrayList<Platform> platforms) {
        super();
        this.platforms = platforms;
        isStanding = false;
        playerId = 1;
        animation = PlayerGenerator.generate();
        animation.setScale(4);
        addActor(animation);
        movement = new Movement(this);
        animation.addComponent(movement);
        movementDrag = new MovementDrag(movement, 0.92f);
        addComponent(movementDrag);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (movement.position.y <= 0) {
            movement.position.set(movement.position.x, 0);
            movement.velocity.set(movement.velocity.x, 0);
            isStanding = true;
        }

        for (Platform p: platforms) {
            Gdx.app.log("Person", movement.position.x + ", " + movement.position.y + ", "
                    + p.getX() + ", " + p.getY() + ", " + p.sprite.getWidth() + ", " + p.sprite.getHeight() + ", "
                    );
            Gdx.app.log("Person", getX() + ", " + getY());
            if (movement.position.x + 150 > p.getX() && movement.position.x < p.getX() + p.sprite.getWidth()-100 &&
                    movement.position.y > p.getY() + p.sprite.getHeight() - 200 && movement.position.y < p.getY() + p.sprite.getHeight()-150) {
                Gdx.app.log("Person", "col");
                movement.position.set(movement.position.x, p.getY() + p.sprite.getHeight()-150);
                movement.velocity.set(movement.velocity.x, 0);
                isStanding = true;
            }
        }


        float ax = 0;
        float ay = -2500;
        float vx = movement.velocity.x;
        float vy = movement.velocity.y;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ax += 400;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ax -= 400;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (isStanding) {
                vy += 3500;
                isStanding = false;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ay -= 100;
        }
        movement.acceleration.set(ax, ay);
        movement.velocity.set(vx, vy);
        // setPosition( 200, 600);
    }
}
