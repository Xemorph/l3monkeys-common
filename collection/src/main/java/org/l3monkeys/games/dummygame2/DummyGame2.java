package org.l3monkeys.games.dummygame2;

import org.l3monkeys.gameloader.GameInterface;

import javafx.stage.Stage;

public class DummyGame2 extends Stage implements GameInterface {

    @Override
    public String getGameTitle() {
        return "Dummy Game 2";
    }

    @Override
    public String getGameDescrption() {
        return "This is another dummy game.";
    }

    @Override
    public String getGameThumbnailPath() {
        return "13Monkeys_Wallpaper.jpg";
    }

    @Override
    public String getGamePreviewPath() {
        return "13Monkeys_Wallpaper.jpg";
    }

    @Override
    public void launchGame() {
        System.out.println("Launching Dummy Game 2");
    }

}