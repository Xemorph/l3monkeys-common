package org.l3monkeys.collection;

import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneController {

    private HashMap<String, Parent> scenes = new HashMap<String, Parent>();
    private Scene parent;

    public SceneController(Scene parent) {
        this.parent = parent;
    }

    /** Injection can be done here */
    public final void addScreen(String name, Parent pane){
        this.scenes.put(name, pane);
    }

    public final void removeScreen(String name){
        this.scenes.remove(name);
    }

    public final void activate(String name){
        this.parent.setRoot(this.scenes.get(name));
    }

    public final Parent getScreen(String name) {
        return this.scenes.get(name);
    }

}