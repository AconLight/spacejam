package assets;

import boost.GameObject;
import boost.SpriteObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AssetLoader {
    private static HashMap<String, Texture> assets = new HashMap<>();
    private static HashMap<String, HashMap<String, Animation<TextureRegion>>> animations = new HashMap<>();

    public static GameObject getAsset(String name) {
        GameObject gameObject = new SpriteObject();
        gameObject.addActor(new Image(assets.get(name)));
        return gameObject;
    }

    public static SpriteObject getAnimation(String name, float index) {
        SpriteObject gameObject = new SpriteObject(index);
        gameObject.animations.putAll(animations.get(name));
        return gameObject;
    }

    public static SpriteObject getAnimation(String name) {
        SpriteObject gameObject = new SpriteObject();
        gameObject.animations.putAll(animations.get(name));
        return gameObject;
    }

    public static GameObject getAsset(String name, float index) {
        GameObject gameObject = new SpriteObject(index);
        gameObject.addActor(new Image(assets.get(name)));
        return gameObject;
    }

    public static void createAsset(String path, String name) {
        assets.put(name, new Texture(Gdx.files.internal(path)));
    }

    public static void load() {
        createAsset("red.png", "redartedSplash");
        createAsset("graphics/sprites/menu_bg.png", "bgSplash");
        createAsset("square.png", "square");
        createAsset("graphics/sprites/quit.png", "quit");
        createAsset("graphics/sprites/play.png", "play");
        loadAnimations();
    }



    public static void createAnimation(String path, String name) {
        HashMap<String, Animation<TextureRegion>> myAnimations = new HashMap<>();

        List<String> dirs = getDirs("core/assets/graphics/animations/" + path);

        for (String child: dirs) {
            Gdx.app.log("AssetLoader", child);
            List<String> dirs2 = getFiles(child);
            TextureRegion[] textures = new TextureRegion[dirs2.size()];
            int i = 0;
            for (String childTexture: dirs2) {
                Gdx.app.log("AssetLoader ------  ", childTexture);
                textures[i] = new TextureRegion(new Texture(childTexture.split("core/assets/")[1]));
                i++;
            }
            Animation<TextureRegion> animation = new Animation<TextureRegion>(1f, textures);
            animation.setPlayMode(Animation.PlayMode.LOOP);
            String[] parts = child.split("/");

            myAnimations.put(parts[parts.length-1], animation);
        }

        animations.put(name, myAnimations);
    }

    public static void loadAnimations() {
        createAnimation("jade", "jade");
    }


    public static List<String> getDirs(String path) {
        List<String> dirs = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            dirs = walk.filter(Files::isDirectory)
                    .map(x -> path + "/" + x.getFileName().toString()).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        dirs.remove(0);
        return dirs;
    }

    public static List<String> getFiles(String path) {
        List<String> dirs = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            dirs = walk.filter(Files::isRegularFile)
                    .map(x -> path + "/" + x.getFileName().toString()).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirs;
    }
}
