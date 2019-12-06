package boost;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class MyScene {
    protected Stage stage;
    protected GameObjectRenderer renderer;

    public MyScene() {
        this.stage = new Stage();
        this.renderer = new GameObjectRenderer(stage);
    }

    public void act() {
        stage.act();
        renderer.render();
    }
}
