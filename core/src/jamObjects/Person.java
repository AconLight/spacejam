package jamObjects;

import boost.GameComponent;
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
    public GameObject animationRight, animationLeft, animation, animationJumpRight, animationJumpLeft;
    public Movement movement;
    public MovementDrag movementDrag;
    ArrayList<Platform> platforms;

    public Person(ArrayList<Platform> platforms, float x, float y) {
        super();
        setPosition(x, y);
        this.platforms = platforms;
        isStanding = false;
        playerId = 0;
        animation = PlayerGenerator.generate();
        animation.setScale(4);
        addActor(animation);
        movement = new Movement(this);
        animation.addComponent(movement);
        movementDrag = new MovementDrag(movement, 0.92f);
        addComponent(movementDrag);
    }

    public void removeAnimations() {
        removeActor(animationRight);
        removeActor(animationLeft);
        removeActor(animationJumpLeft);
        removeActor(animationJumpRight);
        removeActor(animation);
    }

    public void setIdle() {
        removeAnimations();
        addActor(animation);
    }

    public void setRight() {
        removeAnimations();
        addActor(animationRight);
    }
    public void setLeft() {
        removeAnimations();
        addActor(animationLeft);
    }
    public void setJumpRight() {
        removeAnimations();
        addActor(animationJumpRight);
    }
    public void setJumpLeft() {
        removeAnimations();
        addActor(animationJumpRight);
    }

    float downTime = 0;
    boolean isDown = false;

    @Override
    public void act(float delta) {
        super.act(delta);
        if (isDown) {
            downTime += delta;
            if (downTime > 0.5f) {
                isDown = false;
                downTime = 0;
            }
        }

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
            if (movement.velocity.y < 0 && !isDown)
            if (movement.position.x + 150 > p.getX() && movement.position.x < p.getX() + p.sprite.getWidth()-100 &&
                    movement.position.y > p.getY() + p.sprite.getHeight() - 160 && movement.position.y < p.getY() + p.sprite.getHeight()-150) {
                Gdx.app.log("Person", "col");
                movement.position.set(movement.position.x, p.getY() + p.sprite.getHeight()-150);
                movement.velocity.set(movement.velocity.x, 0);
                isStanding = true;
                if (movement.velocity.x >= 0.1f) {
                    setRight();
                }
                else if (movement.velocity.x <= -0.1f) {
                    setLeft();
                }
                else {
                    setIdle();
                }
            }
        }


        float ax = 0;
        float ay = -4500;
        float vx = movement.velocity.x;
        float vy = movement.velocity.y;

        if (playerId == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                ax += 2400;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                ax -= 2400;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (isStanding) {
                    vy += 3300;
                    isStanding = false;
                }
                ay += 1000;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                ay -= 100;
                isDown = true;
            }
        }

        if (playerId == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                ax += 2400;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                ax -= 2400;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                if (isStanding) {
                    vy += 3300;
                    isStanding = false;
                }
                ay += 1000;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                ay -= 100;
                isDown = true;
            }
        }
        movement.acceleration.set(ax, ay);
        movement.velocity.set(vx, vy);
    }
}
