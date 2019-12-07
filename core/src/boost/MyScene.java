package boost;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyScene {
    protected Stage stage;
    protected GameObjectRenderer renderer;

    public MyScene() {
        this.stage = new Stage(new StretchViewport(1920, 1280));
        this.renderer = new GameObjectRenderer(stage);
    }

    public void act() {
        stage.act();
        renderer.render();
    }
}
