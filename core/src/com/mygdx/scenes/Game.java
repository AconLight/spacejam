package com.mygdx.scenes;

import assets.AssetLoader;
import boost.GameObject;
import boost.MyScene;
import com.badlogic.gdx.Gdx;
import jamObjects.Person;
import jamObjects.Projectile;
import wojtek.GameLoader;

import java.util.ArrayList;

public class Game extends MyScene {

    GameObject bg;
    ArrayList<GameObject> gameObjects;
    ArrayList<Projectile> projToRemove;
    float time;
    public boolean isOver;
    public Game() {
        super();
        time = 0;
        projToRemove = new ArrayList<>();
        bg = AssetLoader.getAsset("bg");
        bg.setShader("drunk");
        bg.setScale(4);
        stage.addActor(bg);
        gameObjects = GameLoader.load();
        for (GameObject go: gameObjects) {
            stage.addActor(go);
            Gdx.app.log("siema", "siema");
        }
        isOver = false;
    }



    public void winLeft() {
        stage.addActor();
    }



    public void act() {
        super.act();
        time += Gdx.graphics.getDeltaTime();
        bg.shader.begin();
        bg.shader.setUniformf("time", time/10);
        bg.shader.end();
        for (Projectile projectile: projToRemove) {
            for (GameObject go : gameObjects) {
                if (go instanceof Person) {
                    ((Person) go).projectiles.remove(projectile);
                }
            }
            projectile.remove();
        }
        projToRemove.clear();
    }

    public void addProjectile(Projectile projectile) {
        for (GameObject go : gameObjects) {
            if (go instanceof Person) {
                ((Person) go).projectiles.add(projectile);
            }
        }
        stage.addActor(projectile);
    }

    public void removeProjectile(Projectile projectile) {
        projToRemove.add(projectile);
    }
}
