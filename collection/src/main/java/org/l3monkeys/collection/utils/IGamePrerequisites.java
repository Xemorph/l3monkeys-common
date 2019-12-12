package org.l3monkeys.collection.utils;

import javafx.scene.Scene;

public interface IGamePrerequisites {
    public String getGameTitle();
    public String getGameDescrption();

    // neue gamescene (als Parent, leer)
    public void launchGame(Scene scene);
}
