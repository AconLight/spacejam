package wizard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Coat extends Actor {
    TextureRegion coat = new TextureRegion(new Texture(Gdx.files.internal("wizard/coat.png")));
    public Sprite coatSp;
    TextureRegion renka = new TextureRegion(new Texture(Gdx.files.internal("wizard/renka.png")));
    public Sprite renkaSp;

    public Coat() {
        coatSp = new Sprite(coat);
        renkaSp = new Sprite(renka);
    }

    @Override
    public void act(float delta) {
        coatSp.setPosition(0, -(1-coatSp.getScaleY())*300);
        renkaSp.setScale((float) Math.sqrt(coatSp.getScaleY()));
        renkaSp.setPosition(0, -(1-coatSp.getScaleY())*300);
    }

    @Override
    public void draw(Batch batch, float parentAlfa) {
        renkaSp.draw(batch);
        coatSp.draw(batch);
    }
}
