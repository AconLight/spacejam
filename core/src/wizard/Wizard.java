package wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Wizard extends Group {
    TextureRegion head = new TextureRegion(new Texture(Gdx.files.internal("wizard/ryjec.png")));
    TextureRegion head2 = new TextureRegion(new Texture(Gdx.files.internal("wizard/ryjec2.png")));
    TextureRegion cap = new TextureRegion(new Texture(Gdx.files.internal("wizard/czapka.png")));
    TextureRegion beard = new TextureRegion(new Texture(Gdx.files.internal("wizard/broda.png")));
    Coat coat;

    float life;

    float alfaSpeed = 50;
    float alfa = 7;

    public Wizard(float life, float x, float y) {
        this.life = life;
        coat = new Coat();
        this.setPosition(x, y);
        this.setOrigin(230, 275*2);
        this.addActor(coat);
        coat.setPosition(x, y);
    }

    float lifeSpeed = 30;

    @Override
    public void act(float delta) {
        alfa = 7*30/life;
        if (alfaSpeed > 0) {
            alfaSpeed = 50*30/life;
        } else {
            alfaSpeed = -50*30/life;
        }
        life += delta*lifeSpeed;
        if (life > 100) {
            life = 100;
            lifeSpeed *= -1;
        }

        if (life < 30) {
            life = 30;
            lifeSpeed *= -1;
        }
        coat.coatSp.setScale(life/100);
        this.setOrigin(230, 275*2*life/100);
        if (this.getRotation() > alfa) {
            this.setRotation(alfa);
            alfaSpeed *= -1;
        }
        if (this.getRotation() < -alfa) {
            this.setRotation(-alfa);
            alfaSpeed *= -1;
        }
        this.rotateBy(delta*alfaSpeed);
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlfa) {

        super.draw(batch, parentAlfa);
        batch.draw(beard, getX() - (100-life)*0.4f, getY() - (100-life)*4f);
        batch.draw(head, getX(), getY() - (100-life)*5);
        batch.setColor(1, 1,1, life/100*life/100);
        batch.draw(head2, getX(), getY() - (100-life)*5);
        batch.setColor(1, 1,1, 1);
        batch.draw(cap, getX(), getY() - (100-life)*5);

    }
}
