package jamObjects;

import assets.AssetLoader;
import boost.GameComponent;
import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.gameComponents.Movement;
import com.mygdx.gameComponents.MovementDrag;
import stefan.PlayerGenerator;

import java.util.ArrayList;
import java.util.Random;

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
        generateAnimations();

        //addActor(AssetLoader.getAsset("platform1"));
        movement = new Movement(this);
        addComponent(movement);
        movementDrag = new MovementDrag(movement, 0.92f);
        addComponent(movementDrag);
    }

    void generateAnimations(){
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        animation = PlayerGenerator.generate(r, g, b);
        animation.setScale(4);
        animationRight = PlayerGenerator.generateRight(r, g, b);
        animationRight.setScale(4);
        animationLeft = PlayerGenerator.generateLeft(r, g, b);
        animationLeft.setScale(4);
        animationJumpLeft = PlayerGenerator.generateJumpLeft(r, g, b);
        animationJumpLeft.setScale(4);
        animationJumpRight = PlayerGenerator.generateJumpRight(r, g, b);
        animationJumpRight.setScale(4);

        addActor(animation);
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

        if (movement.position.y <= -200) {
            movement.position.set(movement.position.x, -200);
            movement.velocity.set(movement.velocity.x, 0);
            isStanding = true;
        }

        for (Platform p: platforms) {
            Gdx.app.log("Person", movement.position.x + ", " + movement.position.y + ", "
                    + p.getX() + ", " + p.getY() + ", " + p.sprite.getWidth() + ", " + p.sprite.getHeight() + ", "
                    );
            Gdx.app.log("Person", getX() + ", " + getY());
            if (movement.velocity.y < 0 && !isDown)
            if (movement.position.x + 50 > p.getX() && movement.position.x < p.getX() + p.sprite.getWidth()-20 &&
                    movement.position.y > p.getY() + p.sprite.getHeight() - 130 && movement.position.y < p.getY() + p.sprite.getHeight()-105) {
                Gdx.app.log("Person", "col");
                movement.position.set(movement.position.x, p.getY() + p.sprite.getHeight()-105);
                movement.velocity.set(movement.velocity.x, 0);
                isStanding = true;
                if (movement.velocity.x >= 0.1f) {
                    setRight();
                    Gdx.app.log("dupa", "right");
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
