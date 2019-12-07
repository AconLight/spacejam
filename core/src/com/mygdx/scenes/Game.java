package com.mygdx.scenes;

import assets.AssetLoader;
import boost.GameObject;
import boost.MyScene;
import com.badlogic.gdx.Gdx;
import wojtek.GameLoader;

import java.util.ArrayList;

public class Game extends MyScene {

    GameObject bg;
    public Game() {
        super();
        bg = AssetLoader.getAsset("bg");
        bg.setScale(2);
        //stage.addActor(bg);
        ArrayList<GameObject> gameObjects = GameLoader.load();
        for (GameObject go: gameObjects) {
            stage.addActor(go);
            Gdx.app.log("siema", "siema");
        }
    }
}
