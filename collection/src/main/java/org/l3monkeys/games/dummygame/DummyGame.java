package org.l3monkeys.games.dummygame;

import org.l3monkeys.gameloader.GameInterface;

import javafx.stage.Stage;

public class DummyGame extends Stage implements GameInterface {

    @Override
    public String getGameTitle() {
        return "Dummy Game";
    }

    @Override
    public String getGameDescrption() {
        return "This is a dummy game.";
    }

    @Override
    public String getGameThumbnailPath() {
        return "testBG.png";
    }

    @Override
    public String getGamePreviewPath() {
        return "testBG.png";
    }

    @Override
    public void launchGame() {
        System.out.println("Launching Dummy Game");
    }

}