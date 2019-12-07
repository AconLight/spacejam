package jamObjects;

import assets.AssetLoader;
import boost.GameComponent;
import boost.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameComponents.Movement;
import com.mygdx.gameComponents.MovementDrag;
import stefan.PlayerGenerator;

import java.util.ArrayList;

public class Person extends GameObject {


    public GameObject aim;
    public int playerId;
    public boolean isStanding;
    public GameObject animationRight, animationLeft, animation, animationJumpRight, animationJumpLeft;
    public Movement movement;
    public MovementDrag movementDrag;
    ArrayList<Platform> platforms;
    Skill skill;

    public Person(ArrayList<Platform> platforms, float x, float y) {
        super();
        setPosition(x, y);
        this.platforms = platforms;
        isStanding = false;
        playerId = 0;
        animation = PlayerGenerator.generate();
        animationRight = PlayerGenerator.generate();
        animationLeft = PlayerGenerator.generate();
        animationJumpLeft = PlayerGenerator.generate();
        animationJumpRight = PlayerGenerator.generate();
        animation.setScale(4);
        addActor(animation);
        //addActor(AssetLoader.getAsset("platform1"));
        movement = new Movement(this);
        addComponent(movement);
        movementDrag = new MovementDrag(movement, 0.92f);
        addComponent(movementDrag);

        aim = AssetLoader.getAsset("aim");
        aim.setPosition(75, 150);
    }

    public void setSkill(Skill skill) {
        removeActor(skill);
        this.skill = skill;
        addActor(skill);
    }

    public void setAsPlayer() {
        addActor(aim);
    }

    public void removeAnimations() {
        removeActor(animationRight);
        removeActor(animationLeft);
        removeActor(animationJumpLeft);
        removeActor(animationJumpRight);
        removeActor(animation);
    }

    public void setIdle() {
//        removeAnimations();
//        addActor(animation);
    }

    public void setRight() {
//        removeAnimations();
//        addActor(animationRight);
    }
    public void setLeft() {
//        removeAnimations();
//        addActor(animationLeft);
    }
    public void setJumpRight() {
//        removeAnimations();
//        addActor(animationJumpRight);
    }
    public void setJumpLeft() {
//        removeAnimations();
//        addActor(animationJumpRight);
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
                }
                else if (movement.velocity.x <= -0.1f) {
                    setLeft();
                }
                else {
                    setIdle();
                }
            }
        }

        float aimX = aim.getX();
        float aimY = aim.getY();
        float ax = 0;
        float ay = -4500;
        float vx = movement.velocity.x;
        float vy = movement.velocity.y;

        if (playerId == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                ax += 2400;
                aimX += 500*delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                ax -= 2400;
                aimX -= 500*delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                aimY += 500*delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                if (isStanding) {
                    vy += 3300;
                    isStanding = false;
                }
                ay += 1000;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                aimY -= 500*delta;
                ay -= 100;
                isDown = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.G)) {
                if (skill != null) {
                    skill.use();
                }
            }
        }

        if (playerId == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                ax += 2400;
                aimX += 500*delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                ax -= 2400;
                aimX -= 500*delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                aimY += 500*delta;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.O)) {
                if (isStanding) {
                    vy += 3300;
                    isStanding = false;
                }
                ay += 1000;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                aimY -= 500*delta;
                ay -= 100;
                isDown = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                if (skill != null) {
                    skill.use();
                }
            }
        }
        aimX -= 75;
        aimY -= 150;
        aimX *= 0.96f;
        aimY *= 0.96f;
        aimX += 75;
        aimY += 150;
        aim.setPosition(aimX, aimY);
        movement.acceleration.set(ax, ay);
        movement.velocity.set(vx, vy);
    }
}
