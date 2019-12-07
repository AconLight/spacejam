package jamObjects;

import assets.AssetLoader;
import boost.GameComponent;
import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameComponents.Movement;
import com.mygdx.gameComponents.MovementDrag;
import com.mygdx.scenes.MySceneManager;
import stefan.PlayerGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Person extends GameObject {

    public ArrayList<Projectile> projectiles;
    public GameObject aim;
    public int playerId;
    public boolean isStanding;
    public GameObject animationRight, animationLeft, animation, animationJumpRight, animationJumpLeft;
    public Movement movement;
    public MovementDrag movementDrag;
    ArrayList<Platform> platforms;
    Skill skill;
    int direction;

    public Person(ArrayList<Platform> platforms, float x, float y, int direction) {
        super();
        this.direction = direction;
        projectiles = new ArrayList<>();
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

        aim = AssetLoader.getAsset("aim", 2);
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
        if(skill != null)
            skill.right();
    }
    public void setLeft() {

        removeAnimations();
        addActor(animationLeft);
        if(skill != null)
            skill.left();
    }
    public void setJumpRight() {
        removeAnimations();
        addActor(animationJumpRight);
        if(skill != null)
            skill.right();
    }
    public void setJumpLeft() {
        removeAnimations();
        addActor(animationJumpRight);
        if(skill != null)
            skill.left();
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

        if (playerId != 0) {
            if (movement.position.x <= -100) {
                movement.position.set(-100, movement.position.y);
                movement.velocity.set(-movement.velocity.x * 1.5f, movement.velocity.y + movement.position.y / 4f);
            }

            if (movement.position.x >= 1720) {
                movement.position.set(1720, movement.position.y);
                movement.velocity.set(-movement.velocity.x * 1.5f, movement.velocity.y + movement.position.y / 4f);
            }
        }

        for (Platform p: platforms) {

            if (movement.velocity.y < 0 && !isDown)
            if (movement.position.x + 50 > p.getX() && movement.position.x < p.getX() + p.sprite.getWidth()-20 &&
                    movement.position.y > p.getY() + p.sprite.getHeight() - 130 && movement.position.y < p.getY() + p.sprite.getHeight()-105) {

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

        if (playerId == 0) {
            ax += 2400*direction;
            if (direction > 0) {
                setRight();
            } else if (direction < 0) {
                setLeft();
            } else {
                setIdle();
            }
        }
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
                if (isStanding) {
                    vy += 2500;
                    isStanding = false;
                    if (movement.velocity.x >= 0.1f) {
                        setJumpRight();
                    }
                    else if (movement.velocity.x <= -0.1f) {
                        setJumpLeft();
                    }
                    AssetLoader.jump.play();
                }
                ay += 1700;
                aimY += 500*delta;
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
                if (isStanding) {
                    vy += 2500;
                    isStanding = false;
                    if (movement.velocity.x >= 0.1f) {
                        setJumpRight();
                    }
                    else if (movement.velocity.x <= -0.1f) {
                        setJumpLeft();
                    }
                    AssetLoader.jump.play();
                }
                ay += 1700;
                aimY += 500*delta;
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
        aimX *= 0.92f;
        aimY *= 0.92f;
        aimX += 75;
        aimY += 150;
        aim.setPosition(aimX, aimY);
        movement.acceleration.set(ax, ay);
        movement.velocity.set(vx, vy);

        // collosions
        for (Projectile p: projectiles) {
            if (p.person != this && !p.isDestroyed && !p.person.skill.isUsed)
            if (p.getX() + 20 > getX() && p.getX() < getX() + 150 &&
                    p.getY() + 20 > getY() + 30 && p.getY() < getY() + 280) {
                if (skill instanceof PistolShield) {
                    if (((PistolShield) skill).lives > 0) {
                        ((PistolShield) skill).lives--;
                        ((PistolShield) skill).shield();
                        p.isDestroyed = true;
                        MySceneManager.game.removeProjectile(p);
                        continue;
                    }
                }
                playerId = p.person.playerId;
                p.person.playerId = 0;
                p.isDestroyed = true;
                p.person.skill.isUsed = true;
                p.person.destroy();
                MySceneManager.game.stage.addActor(new Blood(p.person.getX(), p.person.getY()));
                MySceneManager.game.removeProjectile(p);
                Gdx.app.log("Person", "colllllllllllllllllllllllllllllllllllllllllll");
                setAsPlayer();
                Random rand = new Random();
                int r = rand.nextInt(3);
                if (r == 0)
                    setSkill(new Pistol(this));
                if (r == 1)
                    setSkill(new PistolShield(this));
                if (r == 2)
                    setSkill(new Shotgun(this));
            }
        }
    }
    public void destroy() {
        remove();
    }



}
